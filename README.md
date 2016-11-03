## Synopsis

The Simple SQL Builder brings a fluent api to build database statements and queries.

## Usage
   
### Building a `Table`

**Old way**

```java
public class Main{

    public static void main(String[] args){
        String statement = String.format(
                        "CREATE TABLE %s (" +
                                "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                "%s TEXT UNIQUE, " +
                                ");",
                        "tableName",
                        "columnName01",
                        "columnName02");    
    }
    
}
```


**New way**

```java
import me.arndc.simplesqlbuilder.core.Table;
import me.arndc.simplesqlbuilder.core.DataType;

import me.arndc.simplesqlbuilder.builders.TableBuilder;
import me.arndc.simplesqlbuilder.builders.ColumnBuilder;


public class Main{

    public static void main(String[] args){
        Table table = TableBuilder.newTable("tableName")
                             .withColumn(ColumnBuilder.newColumn("columnName01", DataType.INTEGER)
                                             .hasPrimaryKey()
                                             .withAutoIncrement())
                             .withColumn(ColumnBuilder.newColumn("columnName02", DataType.TEXT)
                                             .withUniqueConstraint())
                             .build();
                             
        String statement = table.createStatement(); 
    }
    
}
```

### Building a `Query`


**Old way**
 
 ```java
 
public class Main{

    public static void main(String[] args){
        String query = String.format(Locale.ROOT,
                        "SELECT sum(%s * %s) FROM %s WHERE %s = '%s' OR %s BETWEEN '%s' AND '%s';",
                        columnName01,
                        columnName02,
                        tableName,
                        columnName01,
                        "value",
                        columnName02,
                        "A",
                        "C"
                        );
    }
    
}
```      


**New way**

```java
import me.arndc.simplesqlbuilder.core.Function;
import me.arndc.simplesqlbuilder.core.ArithmeticColumnOperation;
import me.arndc.simplesqlbuilder.core.Operator;
import me.arndc.simplesqlbuilder.core.Table;
import me.arndc.simplesqlbuilder.core.Column;

import me.arndc.simplesqlbuilder.builders.QueryBuilder;
import me.arndc.simplesqlbuilder.builders.WhereChainBuilder;



public class Main{

    public static void main(String[] args){
        Table table;
        Column column01, column02;
        
        // ... initialize table and columns ...
    
        String query = QueryBuilder.newQuery()
                                    .select(Function.sum(ArithmeticColumnOperation.multiply(column01, column02)))
                                    .from(table) // or tableName as a String
                                    .where(WhereChainBuilder
                                        .whereChain(column01.is(Operator.equalsTo("value")))
                                        .or(column02.is(Operator.between("A","C"))))
                                    .buildStatement();
    }
    
}
```   
      