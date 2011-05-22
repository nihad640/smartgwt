
package com.smartgwt.client.docs;

/**
 * <h3>Custom Querying Overview</h3>
 * <b>This feature, called "SQL Templating", is available with Power or better licenses&#010 only.</b> See <a
 * href="http://smartclient.com/product">smartclient.com/product</a> for details.&#010 <p>&#010 The Smart GWT server
 * provides a number of ways to let you customize the SQL or Hibernate&#010 query it generates to fetch data from or update
 * your database.  You can provide full &#010 custom queries in either {@link
 * com.smartgwt.client.docs.serverds.OperationBinding#customSQL SQL} or &#010 {@link
 * com.smartgwt.client.docs.serverds.OperationBinding#customHQL HQL}, or you can replace individual parts of the query
 * &#010 ({@link com.smartgwt.client.docs.serverds.OperationBinding#whereClause the WHERE clause}, for example) while
 * letting&#010 Smart GWT generate the rest. &#010 <P>&#010 Full custom queries specified via &lt;customSQL&gt; provide
 * complete flexibility, but &#010 they cannot be used for automatic data paging; if you use a full custom query, all
 * data&#010 returned by the query will be delivered to the client, which may be inefficient.&#010 To retain automatic data
 * paging, implement your customizations by replacing just specific&#010 clauses of the query, via {@link
 * com.smartgwt.client.docs.serverds.OperationBinding#whereClause &lt;whereClause&gt;}, &#010 {@link
 * com.smartgwt.client.docs.serverds.OperationBinding#selectClause &lt;selectClause&gt;}, and the other
 * clause-by-clause&#010 replacement features.&#010 <p>&#010 Query customization is done per {@link
 * com.smartgwt.client.data.OperationBinding}, so you can create multiple&#010 customized queries per DataSource or even
 * for the same &#010 {@link com.smartgwt.client.data.OperationBinding#getOperationType operation type}.&#010&#010
 * <h4>Using criteria and values</h4>&#010 Whether using full custom queries or individual clauses, your code has access to
 * the &#010 criteria values submitted with the operation; if this is an "add" or "update" operation,&#010 it also has
 * access to the new field values sent from the client.&#010 <p>&#010 Fields are accessed in your SQL or HQL code using the
 * Velocity template language.  You&#010 can refer to container variables <b>$criteria</b> and <b>$values</b> in your
 * queries or &#010 clause snippets, and Smart GWT will insert the appropriate values.  A simple &#010 {@link
 * com.smartgwt.client.docs.serverds.OperationBinding#whereClause whereClause} example:&#010 <p>&#010 <pre><code>&#010 
 * &lt;operationBinding operationType="fetch"&gt;&#010     &lt;whereClause&gt;&#010         continent = $criteria.continent
 * AND population &gt; $criteria.minPop&#010     &lt;/whereClause&gt;&#010  &lt;/operationBinding&gt;&#010
 * </code></pre>&#010 In addition to the $criteria and $values Velocity template variables described above, we&#010 also
 * provide a number of template variables containing generally-useful values.  Please see&#010 {@link
 * com.smartgwt.client.docs.VelocitySupport} for details.&#010&#010 <h4>Using the default clauses</h4>&#010 You also have
 * access to the  default subclauses generated by &#010 Smart GWT.  You can use these in full custom queries to allow a
 * certain part of the query&#010 code to be generated:<p>&#010 <pre>&#010 &lt;customSQL&gt;&#010    SELECT foo, bar FROM
 * $defaultTableClause &#010        WHERE baz > $criteria.baz&#010 &lt;/customSQL&gt;&#010 </pre>&#010 <p>&#010 You can
 * also use them within individual clauses in order to customize a clause without&#010 losing default SQL generation:&#010
 * <pre>&#010 &lt;whereClause&gt;&#010    ($defaultWhereClause) AND foo > 5&#010 &lt;/whereClause&gt;&#010 </pre>&#010&#010
 * <h4>Mixing SQL Templating and custom Java Logic</h4>&#010 You can use both SQL Templating and custom Java logic added
 * via DMI in the same&#010 operationBinding.  Your DMI method is called before SQL is generated, and the SQL template&#010
 * will be evalauted and the actual SQL operation performed only when you call&#010 dsRequest.execute().&#010 <P>&#010 This
 * allows you to modify the criteria or values on the DSRequest, which will change the&#010 values retrieved by $criteria
 * and $values when the SQL Template is evaluated.  You can also&#010 add entirely new information to the Velocity context
 * used to evaluate the template, via&#010 the server-side API DSRequest.addToTemplateContext().&#010&#010 <h4>Excluding
 * fields from SQL Generation</h4>&#010 In some cases you pass a value to the server which is intended to be used by custom
 * Java&#010 logic and should not cause SQL to be generated.  To prevent all SQL from being generated for&#010 a particular
 * field, set {@link com.smartgwt.client.data.DataSourceField#getCustomSQL customSQL="true"} on that field.  &#010 <P>&#010
 * Any field for which SQL will ever be generated must be declared in a DataSource.  It's&#010 common to have a field which
 * is only used in one or two operationBindings - in this case,&#010 set customSQL="true" on the field, and use {@link
 * com.smartgwt.client.docs.serverds.OperationBinding#customFields customFields} to cause&#010 specific operationBindings
 * to generate SQL for the field, while all others ignore it.&#010 <P>&#010 In other cases you want to hand-write SQL for a
 * particular field for a specific&#010 operationBinding.  You can set {@link
 * com.smartgwt.client.docs.serverds.OperationBinding#excludeCriteriaFields excludeCriteriaFields} to &#010 exclude fields
 * from SQL generation for the whereClause of a specific operationBinding.&#010&#010 <h4>Field-level SQL
 * Customization</h4>&#010 An individual field can configured with custom expressions to be used in different SQL&#010
 * statements for all operationBindings - see {@link com.smartgwt.client.data.DataSourceField#getCustomSQL customSQL} for
 * an overview.&#010 &#010 <h4>Using AdvancedCriteria</h4>&#010 The above examples involving $criteria assume that the
 * submitted criteria are simple&#010 Criteria and not {@link com.smartgwt.client.data.AdvancedCriteria}, a more
 * sophisticated criteria format in which&#010 different search operators can be specified per field and criteria can be
 * nested.  &#010 <P>&#010 The special variable $advancedCriteria provides simplified access to the AdvancedCriteria&#010
 * structure: $advancedCriteria.<i>fieldName</i> will return the criteria value specified for a&#010 given fieldName,
 * regardless of where it's present in the AdvancedCriteria.&#010 <P>&#010 This makes it straightforward to add an
 * additional criteria value to AdvancedCriteria that&#010 you want to use only in the SQL template:&#010 <ul>&#010 <li>
 * make a simple Criteria object representing the fieldName and value name you need to have&#010 available in the SQL
 * template&#010 <li> use {@link com.smartgwt.client.data.DataSource#combineCriteria DataSource.combineCriteria} to add
 * your additional criteria to an existing&#010 AdvancedCriteria, wherever this is convenient&#010 <li> list the fieldName
 * in {@link com.smartgwt.client.docs.serverds.OperationBinding#customCriteriaFields customCriteriaFields} to prevent
 * the&#010 default SQL for this field from being generated&#010 <li> use $advancedCriteria in your customized SQL to
 * access the value&#010 </ul>&#010 Java API dsRequest.getCriteriaValue() is equivalent to accessing $advancedCriteria in a
 * SQL&#010 Template.  Also note, if a given fieldName appears more than once in AdvancedCriteria,&#010
 * $advancedCriteria.<i>fieldName</i> will return the value for the first {@link com.smartgwt.client.data.Criterion}
 * that&#010 uses the fieldName, as found by depth-first search.&#010&#010 <h4>Stored procedures</h4>&#010 It is possible
 * to include templated calls to SQL stored procedures in a&#010 {@link
 * com.smartgwt.client.docs.serverds.OperationBinding#customSQL customSQL} clause, for the ultimate in flexibility.  For
 * &#010 example, the deletion of an order might require a number of actions: deletion of the order&#010 record itself,
 * messages sent to other systems (data warehousing, maybe, or a central accounts&#010 system running on a mainframe), an
 * event log written, and so on.  You could write a stored &#010 procedure to do all this, and then invoke it with a
 * customSQL clause:&#010 <pre>&#010    &lt;operationBinding operationType="remove"&gt;&#010        &lt;customSQL&gt;call
 * deleteOrder($criteria.orderNo)&lt;/customSQL&gt;&#010    &lt;/operationBinding&gt;&#010 </pre>&#010&#010 <h4>Velocity
 * Template Language conditional logic</h4>&#010 When writing a customized SQL clause for an operation, it is commonly
 * desirable to be&#010 able to include conditional logic - for example only modifying a where clause if a&#010 certain
 * criteria value is present. Velocity template language conditional statements&#010 can be embedded directly into your
 * template code to achieve this. For example the following&#010 <code>whereClause</code> would produce different output
 * depending on whether the &#010 request criteria included a value for the field <code><i>someField</i></code>:<p>&#010
 * <code>&lt;whereClause&gt;$defaultWhereClause #if ($criteria.someField) AND someDatabaseField = $criteria.someField
 * #end&lt/whereClause&gt;</code>&#010 <p>&#010 If <code><i>criteria.someField</i></code> was not present in the request,
 * the generated&#010 SQL statement would simply use the default where clause -- otherwise&#010 <code>AND someDatabaseField
 * = <i>[some value]</i></code> would be appended to it (where&#010 <code><i>[some value]</i></code> was picked up from the
 * value of <code>someField</code> on &#010 the request criteria object).&#010&#010 <h4>Custom queries are safe</h4>&#010
 * Custom queries are protected from <a href=http://en.wikipedia.org/wiki/SQL_injection>&#010 SQL injection attacks</a>,
 * because anything coming from the client is quoted and escaped &#010 in accordance with the syntax of the underlying
 * database before use (though see the warning&#010 about using <code>$rawValue</code> in the article on {@link
 * com.smartgwt.client.docs.VelocitySupport}).&#010 So, in a typical SQL injection attack an attacker might enter his User
 * ID as <br>&#010 &nbsp;&nbsp;<code>123' OR '1' = '1</code><p>&#010 in the hope that this will generate a query&#010 with
 * a where clause like this<br>&#010  &nbsp;&nbsp;<code>WHERE userID = '123' OR '1' = '1'</code><p>&#010 which would of
 * course return every row.  With Smart GWT custom queries, this does not happen; &#010 the client-provided string is
 * escaped, and the resultant clause would look like this: <br>&#010 &nbsp;&nbsp;<code>WHERE userID = '123'' OR ''1'' =
 * ''1'</code><p>&#010 This clause only returns those records where the userID column contains the literal value that &#010
 * the user typed: <br>&#010 &nbsp;&nbsp;<code>123' OR '1' = '1</code>&#010 <p>&#010 Further, custom queries can be
 * protected from buggy or ad-hoc client requests because the &#010 query is specified on the server.  For example you
 * could add a custom where clause, as shown&#010 in the above section on default clauses, to ensure that certain records
 * are never seen by&#010 the client.  For instance: <p>&#010 <code>&lt;whereClause&gt;($defaultWhereClause) AND
 * confidential = '0'&lt;/whereClause&gt;</code>.&#010 <p>&#010 <h4>Column case-sensitivity issues</h4>&#010 Different
 * database products have different rules concerning case-sensitivity in column &#010 names.  Consider the following
 * query:&#010 <br><br><code>&nbsp;&nbsp;SELECT orderNumber FROM Order</code>&#010 <ul>&#010 <li>MySQL and Microsoft SQL
 * Server are not case-sensitive with regard to column names, so &#010 this query will work whether the column is called
 * "orderNumber" or "ORDERNUMBER" or any &#010 other variation.</li>&#010 <li>Oracle, HSQLDB and DB2 default to upper-case
 * column names.  Therefore, this query will&#010 fail if the column is actually called "orderNumber"; it will only work if
 * the underlying&#010 column name is "ORDERNUMBER"</li>&#010 <li>PostgreSQL defaults to lower-case column names, so this
 * query will fail unless the &#010 underlying column name is actually "ordernumber"</li>&#010 </ul>&#010 Note that these
 * differences only apply in a practical sense if the underlying database &#010 table was created with quoted column names.
 * If you create your tables without quoting &#010 column names, the database creates the columns using its own preferred
 * defaults, which &#010 is what it will also use when it encounters an unquoted column name in a query.  Behind &#010 the
 * scenes, the differences are still there - your column will be called "ORDERNUMBER" &#010 on Oracle and "ordernumber" on
 * PostgreSQL - but that wouldn't be apparent unless you went &#010 looking for it: the example query would work unchanged
 * with both products, and you would &#010 be able to use whatever mixture of case you like in your DataSource field names
 * &#010 (Smart GWT will map DataSource field "orderNumber" to Oracle column "ORDERNUMBER" &#010 transparently).  <b>This
 * is the recommended approach.</b>&#010 <p>&#010 If you can't, or don't want to, accept the database default - if you are
 * working with an&#010 existing schema, for example - then you will need to quote column names in your queries.&#010
 * Unfortunately, the way you do this also differs by database product, so quoting a column&#010 name correctly in one
 * database's syntax may mean that the query cannot be ported to a&#010 different database without change.&#010 <p>&#010 To
 * help with this case, we provide two extra container variables that you can use.&#010 <b>$fields</b> contains the names
 * of all the fields in your DataSource, but quoted in&#010 accordance with the column-quoting rules of the target
 * database.  <b>$qfields</b> also&#010 contains a list of field names, but in this case each one is qualified with its
 * table&#010 name.<p>&#010 As an example of how to use <b>$fields</b> and <b>$qfields</b>, consider a DataSource with&#010
 * a field called "itemID", bound to a column also called "itemID", and a tableName property &#010 of "orderItem".  Here
 * are three ways to write a {@link com.smartgwt.client.docs.serverds.OperationBinding#selectClause selectClause} &#010 for
 * a custom SQL query that returns that field:<ul>&#010 <li><code>orderItem."itemID"</code>&#010
 * <li><code>orderItem.$fields.itemID</code>&#010 <li><code>$qfields.itemID</code>&#010 </ul>&#010 The first of these is
 * not portable.  It will work fine in HSQL and Oracle, but will fail &#010 with a syntax error in MySQL because you quote
 * a field name with backticks in MySQL, not &#010 quote marks.&#010 <p>&#010 The usages via <b>$fields</b> and
 * <b>$qfields</b> <em>are</em> portable.  The second line, &#010 when targeting Oracle, will be translated to
 * <code>orderItem."itemID"</code>; when targeting&#010 MySQL, it will be translated to <code>orderItem.itemID</code>, or
 * <code>orderItem.`itemID`</code>&#010 if column quoting is enabled for that database (it generally isn't required, since
 * MySQL &#010 preserves case by default).
 * @see com.smartgwt.client.docs.serverds.OperationBinding#selectClause
 * @see com.smartgwt.client.docs.serverds.OperationBinding#tableClause
 * @see com.smartgwt.client.docs.serverds.OperationBinding#whereClause
 * @see com.smartgwt.client.docs.serverds.OperationBinding#groupClause
 * @see com.smartgwt.client.docs.serverds.OperationBinding#orderClause
 * @see com.smartgwt.client.docs.serverds.OperationBinding#valuesClause
 * @see com.smartgwt.client.docs.serverds.OperationBinding#customSQL
 * @see com.smartgwt.client.docs.serverds.OperationBinding#customHQL
 * @see com.smartgwt.client.docs.serverds.OperationBinding#customFields
 * @see com.smartgwt.client.docs.serverds.OperationBinding#customValueFields
 * @see com.smartgwt.client.docs.serverds.OperationBinding#customCriteriaFields
 * @see com.smartgwt.client.docs.serverds.OperationBinding#excludeCriteriaFields
 * @see com.smartgwt.client.docs.serverds.OperationBinding#useForCacheSync
 * @see com.smartgwt.client.docs.serverds.OperationBinding#cacheSyncOperation
 * @see com.smartgwt.client.docs.serverds.OperationBinding#canSyncCache
 * @see com.smartgwt.client.docs.serverds.OperationBinding#sqlType
 * @see com.smartgwt.client.types.SQLType
 */
public interface CustomQuerying {
}
