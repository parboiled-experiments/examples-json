package org.parboiled.json;

import org.parboiled.Action;
import org.parboiled.annotations.BuildParseTree;
import org.parboiled.matchers.Matcher;
import org.parboiled.support.ParseTreeUtils;
import org.parboiled.support.ParsingResult;

@BuildParseTree
public class DeclSQLParser extends DeclParser<Object> {

	protected void initLexer() {
		super.initLexer();

		LEXER.put("_tableName", _w);

		LEXER.put("_columnName", _w);
		LEXER.put("_columnValue", _l);

		LEXER.put("_columnNames",
				(Matcher) seq(LEXER.get("_columnName"), __, _0n(seq(__, ',', __, LEXER.get("_columnName"))))
						.suppressNode());

		LEXER.put("_columnExpr", (Matcher) _1of(LEXER.get("_columnName"), LEXER.get("_columnValue")).suppressNode());

		LEXER.put("_op", (Matcher) AnyOf("<>=").label("spec_chars:op"));

	}

	protected void initActions() {
		ACTIONS.put("_nowhere", _nowhere);
	}

//	protected static String json = " { " //
//			+ "\n 	\"sql_stmt\" :  { " //
//			+ "\n			\"select_stmt\" : { " //
//			+ "\n				\"seq\" : [ \"__\", { \"i\" : \"select\" }, \"__\", " //
//			+ "\n 				{ \"_1of\" : [ \"*\", \"_columnNames\" ] }, " //
//			+ "\n 				\"__\", { \"i\" : \"from\"}, \"__\", \"%select_expr%\", \"__\", " //
//			+ "\n					{ \"_01\" : { \"seq\" : [ { \"_01\" : { \"i\" : \"as\" } }, \"__\", \"_tableName\", \"_nowhere\" ] } }, " //
//			+ "\n					\"__\", " //
//			+ "\n					{ \"_01\" : { \"seq\" : [ { \"i\" : \"where\"}, \"__\", \"_columnExpr\", \"__\", \"_op\", \"__\", \"_columnExpr\" ] } } "
//			+ "\n				] " //
//			+ "\n			} " //
//			+ "\n		}, " //
//			+ "\n 	\"select_expr\" : { " //
//			+ "\n				\"_1of\" : [ \"_w\", { " //
//			+ "\n					\"seq\" : [ \"(\", \"%select_stmt%\", \")\" ] " //
//			+ "\n			} ] " //
//			+ "\n		} " //
//			+ "\n }";

//	public DeclSQLParser() {
//		super("sql_stmt", json);
//	}
	
	public DeclSQLParser() {
		super("sql_stmt", DeclSQLParser.class.getClassLoader().getResourceAsStream("sql-para.json"));
	}

	// Skip Mains

	Action<?> _nowhere = context -> {
		return !context.getMatch().equalsIgnoreCase("where");
	};

    private static DeclParserRunner<Object> parser = new DeclParserRunner<>(DeclSQLParser.class);

    private static void parse(String string) throws Exception {

		ParsingResult<?> result = parser.parse(string);

		System.out.println("--------------------------------------------");
		System.out.println(" sql : " + string);
		System.out.println("--------------------------------------------");

		String parseTreePrintOut = ParseTreeUtils.printNodeTree(result);
		System.out.println("tree : " + parseTreePrintOut);

	}

    public static void main(String[] args) throws Exception {
        
		parse(" select * from employee ");
//		parse(" select * from Employee1 ");		
//		parse(" Select * From Employee1 ");
//
//		parse(" Select a From employee ");
//		parse(" Select a , b From employee ");
//		parse(" Select a , b From employee worker ");
//		parse(" Select a , b , c  From employee as worker ");
//		
//		parse(" Select * from employee as toppers where rank > 4");
//		parse(" Select * from employee where rank > 4");
//		
//		parse(" select * from ( select * from employee ) ");
//		parse(" select * from ( select * from employee ) where rank > 4 ");
//		parse(" select * from ( select * from employee ) worker where rank > 4 ");
//		parse(" select * from ( select * from employee where rank < 4 )");
//		parse(" select * from ( select * from employee where rank < 4 ) toppers");
//		parse(" select * from ( select * from employee where rank < 4 ) as toppers");

//		parse(" Insert Into employee1 values ( 1 , \"ab\" ) ");
//
//		parse(" Insert Into employee2 ( a , b ) values ( 1 , \"ab\" )");
//		parse(" Insert Into toppers select * from employee where a > 3 ");
//		parse(" Insert Into toppers select * from employee where a < 3 ");
//		parse(" Insert Into toppers ( a ) select a  from employee where a > 3 ");
//		parse(" Insert Into toppers ( a , b ) select a , b from employee where a > 3 ");	

//		parse("create table test ( a int )");
//
//		parse("create table if not exists test ( a int )");
//		parse("create table test as select * from pass");

    }

}