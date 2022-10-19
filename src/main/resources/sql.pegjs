sql_stmt
  = select_stmt

select_stmt
  = __ "SELECT"i __ ( '*' / _columnNames ) 
    __ "FROM"i __ select_expr 
    __ ( __ "as"i? __ _tableName)?
    __ ("WHERE"i __ _columnExpr __ _op __ _columnExpr)? __ 

select_expr = _tableName / '(' __ select_stmt __ ')'

_columnExpr = _columnName / _columnValue
_columnValue = _l
_columnNames = _columnName __ (__ ',' __ _columnName)*
_columnName = _w
_tableName = _w
  
_op = [=><]
_l = "'" _w "'"  / "\"" _w "\"" / _d
_w = [A-Za-z][A-Za-z0-9]*
_d = [0-9]+
__ = [ \t\r\n]*