package ru.geekbrains.lesson7.orm;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.util.*;

public class Repository<T> {

    private final Connection conn;
    private Class<T> clazz;

    public Repository(Connection conn, Class<T> clazz) throws SQLException{
        this.conn = conn;
        this.clazz = clazz;
    }

    public void insertO(User user) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "insert into users(login, password) values (?, ?);")) {
            stmt.setString(1, user.getLogin());
            stmt.setString(2, user.getPassword());
            stmt.execute();
        }
    }

    public void insert(T obj) throws SQLException {

        if (!clazz.isAnnotationPresent(Table.class)) {
            throw new IllegalStateException("No Table annotation");
        }
        String tableName = clazz.getAnnotation(Table.class).tableName();
        tableName = tableName.isEmpty() ? clazz.getSimpleName() : tableName;

        StringBuilder sb = new StringBuilder();
        sb.append("insert into " + tableName + "(");
        StringBuilder vl = new StringBuilder();
        vl.append(" values (");

        Map<String, Object> nameAndValue = new LinkedHashMap<>();

        for (Field fld : clazz.getDeclaredFields()) {
            if (!fld.isAnnotationPresent(ru.geekbrains.lesson7.orm.Field.class) ||
                    fld.isAnnotationPresent(PrimaryKey.class)) {
                continue;
            }
            ru.geekbrains.lesson7.orm.Field fldAnnotation = fld.getAnnotation(ru.geekbrains.lesson7.orm.Field.class);
            String fieldName = fldAnnotation.name().isEmpty() ? fld.getName() : fldAnnotation.name();
            try {
                Object value = clazz.getMethod("get" + fieldName.substring(0, 1)
                        .toUpperCase() + fieldName.substring(1), null)
                        .invoke(obj);
                if (value != null) {
                    nameAndValue.put(fieldName, value);
                    sb.append(fieldName + ",");
                    vl.append("?,");
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

        }

        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");
        if (vl.length() > 0) {
            vl.deleteCharAt(vl.length() - 1);
            vl.append(")");
            sb.append(vl);
        }
        sb.append(";");

        try (PreparedStatement stmt = conn.prepareStatement(sb.toString())) {
            int i = 1;
            for (Map.Entry<String, Object> keyVal : nameAndValue.entrySet()) {
                if (keyVal.getValue() != null) {
                    if (keyVal.getValue() instanceof Integer) {
                        stmt.setInt(i, (Integer) keyVal.getValue());
                    } else if (keyVal.getValue() instanceof String) {
                        stmt.setString(i, (String) keyVal.getValue());
                    } else if (keyVal.getValue() instanceof Boolean) {
                        stmt.setBoolean(i, (Boolean) keyVal.getValue());
                    } else if (keyVal.getValue() instanceof Byte) {
                        stmt.setByte(i, (Byte) keyVal.getValue());
                    } else if (keyVal.getValue() instanceof Float) {
                        stmt.setFloat(i, (Float) keyVal.getValue());
                    } else if (keyVal.getValue() instanceof Double) {
                        stmt.setDouble(i, (Double) keyVal.getValue());
                    } else if (keyVal.getValue() instanceof Long) {
                        stmt.setLong(i, (Long) keyVal.getValue());
                    } else if (keyVal.getValue() instanceof Short) {
                        stmt.setShort(i, (Short) keyVal.getValue());
                    } else if (keyVal.getValue() instanceof Blob) {
                        stmt.setBlob(i, (Blob) keyVal.getValue());
                    }
                    i++;
                }
            }
            stmt.execute();
        }
    }

    public User findByLogin(String login) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(
                "select id, login, password from users where login = ?")) {
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new User(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
        }
        return new User(-1, "", "");
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> res = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery("select id, login, password from users");

            while (rs.next()) {
                res.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        }
        return res;
    }

    public void createTableIfNotExists(String stringSQL) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(stringSQL);
        }
    }

    public String buildCreateTableStatement() {
        StringBuilder sb = new StringBuilder();
        StringBuilder ind = new StringBuilder();
        if (!clazz.isAnnotationPresent(Table.class)) {
            throw new IllegalStateException("No Table annotation");
        }
        String tableName = clazz.getAnnotation(Table.class).tableName();
        tableName = tableName.isEmpty() ? clazz.getSimpleName() : tableName;
        sb.append("create table if not exists " + tableName + "(");

        for (Field fld : clazz.getDeclaredFields()) {
            if (!fld.isAnnotationPresent(ru.geekbrains.lesson7.orm.Field.class)) {
                continue;
            }
            ru.geekbrains.lesson7.orm.Field fldAnnotation = fld.getAnnotation(ru.geekbrains.lesson7.orm.Field.class);
            String fieldName = fldAnnotation.name().isEmpty() ? fld.getName() : fldAnnotation.name();
            int fieldLength = fldAnnotation.length();
            String fieldType = null;
            Class<?> type = fld.getType();
            if (type == int.class) {
                fieldType = "int";
            } else if (type == String.class) {
                if (fieldLength == 0) {
                    fieldType = "varchar(25)";
                } else {
                    fieldType = "varchar(" + fieldLength + ")";
                }
            } else if (type == boolean.class) {
                fieldType = "bit";
            } else if (type == byte.class) {
                fieldType = "tinyint";
            } else if (type == char.class) {
                fieldType = "char";
            } else if (type == double.class) {
                fieldType = "double";
            } else if (type == float.class) {
                fieldType = "real";
            } else if (type == long.class) {
                fieldType = "bigint";
            } else if (type == short.class) {
                fieldType = "smallint";
            } else if (type == java.util.Date.class) {
                fieldType = "datetime";
            } else if (type == java.io.Serializable.class) {
                fieldType = "blob";
            }

            boolean isPrimaryKey = fld.isAnnotationPresent(PrimaryKey.class);
            boolean isNotNull = fld.isAnnotationPresent(NotNull.class);
            boolean isUnique = fld.isAnnotationPresent(Unique.class);
            boolean isIndex = fld.isAnnotationPresent(Index.class);

            sb.append(fieldName + " " + fieldType +
                    (isPrimaryKey ? " primary key" : "") +
                    (isNotNull ? " not null" : "") +
                    (isUnique ? " unique" : "") +
                    ",");
            if (isIndex)
                ind.append(" index uq_" + fieldName + "(" + fieldName + "),");
        }

        if (ind.length() > 0)
            sb.append(ind);

        sb.deleteCharAt(sb.length() - 1);
        sb.append(");");
        return sb.toString();
    }
}