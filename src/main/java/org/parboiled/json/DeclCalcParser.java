package org.parboiled.json;

import org.parboiled.annotations.BuildParseTree;

import org.parboiled.support.ParseTreeUtils;
import org.parboiled.support.ParsingResult;

@BuildParseTree
public class DeclCalcParser extends DeclParser<Object> {

//	{
//	 	"expr" : { "seq" : [ "__", "_d" , "__", { "_0n" : { "seq" : [ { "AnyOf" : "+-" }, "__", "_d", "__" ] } } ] }
//	}

//	{
//	 	"expr" : { "seq" : [ "__", "%term%", "__", { "_0n" : { "seq" : [ { "AnyOf" : "+-" }, "__", "%term%", "__" ] } } ] },
//	 	"term" : _d
//	}

//	{
// 		"expr" : { "seq" : [ "__", "%term%", "__", { "_0n" : { "seq" : [ { "AnyOf" : "+-" }, "__", "%term%", "__" ] } } ] },
//	 	"term" : { "seq" : "%factor%", "__", { "_0n" : { "seq" : [ { "AnyOf" : "+-" }, "__", "%factor%" ] } } ] },
//	 	"factor" : "_d"
//	}

	private static String inputjson = "\n {"//
			+ "\n 	\"expr\" : { \"seq\" : [ \"__\", \"%term%\", \"__\", { \"_0n\" : { \"seq\" : [ { \"AnyOf\" : \"+-\" }, \"__\", \"%term%\", \"__\" ] } } ] }, " //
			+ "\n	\"term\" : { \"seq\" : [ \"%factor%\", \"__\", { \"_0n\" : { \"seq\" : [ { \"AnyOf\" : \"*/\" }, \"__\", \"%factor%\" ] } } ] }, "//
			+ "\n 	\"factor\" : { \"_1of\" : [ \"_d\", { \"seq\":[\"(\",\"%expr%\",\")\" ] } ] } " //
			+ "\n }";

	public DeclCalcParser() {
		super("expr", inputjson);
	}

    private static DeclParserRunner<Object> parser = new DeclParserRunner<>(DeclCalcParser.class);

    private static void parse(String string) throws Exception {

		ParsingResult<?> result = parser.parse(string);

		System.out.println("--------------------------------------------");
		System.out.println(" expr : " + string);
		System.out.println("--------------------------------------------");

		String parseTreePrintOut = ParseTreeUtils.printNodeTree(result);
		System.out.println("tree : " + parseTreePrintOut);

	}

    public static void main(String[] args) throws Exception {
        
        parse(" 22 + 33 ");
		parse(" 22 + 33 * 44  ");
		parse(" 22 + ( 33 * 44 ) ");

    }

}