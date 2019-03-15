package executor.reflection;

import java.util.List;

public class ReflectionSQLBulder<T> {


    public String selectSQL(Class clazz) {

        StringBuilder sql = new StringBuilder("select * from ");
        sql.append(clazz.getSimpleName());
        sql.append(" where ");
        sql.append(ReflectionHelper.getIdName(clazz));
        sql.append(" = ?");

        return sql.toString();
    }

    public String insertSQL(T objectData) {

        StringBuilder sql = new StringBuilder("insert into ");
        sql.append(ReflectionHelper.getTableName(objectData));
        sql.append(" (");
        List<String> names = ReflectionHelper.getFieldsNames(objectData);
        fillListOfColumns(names,sql);
        sql.append(" values (");
        fillListOfValues(names,sql);
        return sql.toString();
    }

    public String updateSQL(T objectData) {
        StringBuilder sql = new StringBuilder("update ");

        sql.append(ReflectionHelper.getTableName(objectData));
        sql.append(" set (");
        List<String> names = ReflectionHelper.getFieldsNames(objectData);
        fillListOfColumns(names,sql);
        sql.append(" = (");
        fillListOfValues(names,sql);
        sql.append(" where ");
        sql.append(ReflectionHelper.getIdName(objectData.getClass()));
        sql.append(" = ?");

        return sql.toString();
    }

    private void fillListOfColumns(List<String> columns, StringBuilder sql) {
        for (int i = 0; i < columns.size(); i++) {
            sql.append(columns.get(i));
            if (i == columns.size() - 1) {
                sql.append(")");
            } else {
                sql.append(" ,");
            }
        }
    }

    private void fillListOfValues(List<String> values, StringBuilder sql) {
        for (int i = 0; i < values.size(); i++) {
            sql.append("?");
            if (i == values.size() - 1) {
                sql.append(")");
            } else {
                sql.append(" ,");
            }
        }
    }
}
