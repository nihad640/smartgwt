/*
 * Smart GWT (GWT for SmartClient)
 * Copyright 2008 and beyond, Isomorphic Software, Inc.
 *
 * Smart GWT is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation.  Smart GWT is also
 * available under typical commercial license terms - see
 * http://smartclient.com/license
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 */
 
package com.smartgwt.client.widgets.form;



import com.smartgwt.client.event.*;
import com.smartgwt.client.core.*;
import com.smartgwt.client.types.*;
import com.smartgwt.client.data.*;
import com.smartgwt.client.data.events.*;
import com.smartgwt.client.rpc.*;
import com.smartgwt.client.widgets.*;
import com.smartgwt.client.widgets.events.*;
import com.smartgwt.client.widgets.form.*;
import com.smartgwt.client.widgets.form.validator.*;
import com.smartgwt.client.widgets.form.fields.*;
import com.smartgwt.client.widgets.tile.*;
import com.smartgwt.client.widgets.tile.events.*;
import com.smartgwt.client.widgets.grid.*;
import com.smartgwt.client.widgets.grid.events.*;
import com.smartgwt.client.widgets.layout.*;
import com.smartgwt.client.widgets.menu.*;
import com.smartgwt.client.widgets.tab.*;
import com.smartgwt.client.widgets.toolbar.*;
import com.smartgwt.client.widgets.tree.*;
import com.smartgwt.client.widgets.tree.events.*;
import com.smartgwt.client.widgets.viewer.*;
import com.smartgwt.client.widgets.calendar.*;
import com.smartgwt.client.widgets.calendar.events.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.Element;
import com.smartgwt.client.util.JSOHelper;
import com.smartgwt.client.util.EnumUtil;
import com.google.gwt.event.shared.*;
import com.google.gwt.event.shared.HasHandlers;

/**
 * The ValuesManager manages data from multiple member forms. <P> If a single logical form needs to be separated into
 * multiple DynamicForm instances for Layout purposes (for example, spanning one logical form across multiple Tabs), a
 * ValuesManager can be used to make the forms act as one logical form, supporting all value-related APIs otherwise called
 * on DynamicForm directly. <P> A ValuesManager has no visual representation - it is strictly a logical entity, and the
 * member forms provide the user interface.  You can initialize a ValuesManager with a set of member forms (by setting
 * {@link com.smartgwt.client.widgets.form.ValuesManager#getMembers members} at init) or add and remove member forms
 * dynamically. <P> Calling {@link com.smartgwt.client.widgets.form.ValuesManager#setValues} on a ValuesManager will
 * automatically route new field values to whichever member form is showing an editor for that field.  Likewise, calling
 * {@link com.smartgwt.client.widgets.form.ValuesManager#validate} will validate all member forms, and {@link
 * com.smartgwt.client.widgets.form.ValuesManager#saveData} will initiate a save operation which aggregates values from all
 * member forms. <P> Like a DynamicForm, a ValuesManager can be databound by setting {@link
 * com.smartgwt.client.widgets.form.ValuesManager#getDataSource dataSource}.  In this case all member forms must also be
 * bound to the same DataSource. <P> In general, when working with a ValuesManager and its member forms, call APIs on the
 * ValuesManager whenever you are dealing with values that span multiple forms, and only call APIs on member forms that are
 * specific to that form or its fields. <P> Note that, just as a DynamicForm can track values that are not shown in any
 * FormItem, a ValuesManager may track values for which there is no FormItem in any member form.  However, when using a
 * ValuesManager these extra values are only allowed on the ValuesManager itself. Member forms will not track values for
 * which they do not have FormItems.
 */
public class ValuesManager extends BaseClass {

    public static ValuesManager getOrCreateRef(JavaScriptObject jsObj) {
        if(jsObj == null) return null;
        BaseClass obj = BaseClass.getRef(jsObj);
        if(obj != null) {
            return (ValuesManager) obj;
        } else {
            return new ValuesManager(jsObj);
        }
    }

    public ValuesManager(){
        scClassName = "ValuesManager";
    }

    public ValuesManager(JavaScriptObject jsObj){
        super(jsObj);
    }

    public native JavaScriptObject create()/*-{
        var config = this.@com.smartgwt.client.core.BaseClass::getConfig()();
        return $wnd.isc.ValuesManager.create(config);
    }-*/;
    // ********************* Properties / Attributes ***********************

    /**
     * The error message for a failed validator that does not specify its own errorMessage.
     *
     * @param unknownErrorMessage unknownErrorMessage Default value is "Invalid value"
     */
    public void setUnknownErrorMessage(String unknownErrorMessage) {
        setAttribute("unknownErrorMessage", unknownErrorMessage, true);
    }

    /**
     * The error message for a failed validator that does not specify its own errorMessage.
     *
     *
     * @return String
     */
    public String getUnknownErrorMessage()  {
        return getAttributeAsString("unknownErrorMessage");
    }

    /**
     * If set to true, client-side validators will not run on the form when validate() is called.  Server-side validatiors (if
     * any) will still run on attempted save.
     * <p><b>Note : </b> This is an advanced setting</p>
     *
     * @param disableValidation disableValidation Default value is null
     */
    public void setDisableValidation(Boolean disableValidation) {
        setAttribute("disableValidation", disableValidation, true);
    }

    /**
     * If set to true, client-side validators will not run on the form when validate() is called.  Server-side validatiors (if
     * any) will still run on attempted save.
     *
     *
     * @return Boolean
     */
    public Boolean getDisableValidation()  {
        return getAttributeAsBoolean("disableValidation");
    }
             
    /**
     * Default {@link com.smartgwt.client.types.DSOperationType} to be performed when {@link
     * com.smartgwt.client.widgets.form.DynamicForm#saveData} is called. This property is automatically set on a call to {@link
     * com.smartgwt.client.widgets.form.DynamicForm#editRecord} or {@link
     * com.smartgwt.client.widgets.form.DynamicForm#editNewRecord}, or may be set directly via  {@link
     * com.smartgwt.client.widgets.form.DynamicForm#setSaveOperationType}. <P> If <code>saveOperationType</code> is unset, the
     * form will heuristically determine whether an "add" or "update" operation is intended based on whether the primaryKey
     * field is present and editable.
     * Setter for the default {@link com.smartgwt.client.types.DSOperationType} when {@link com.smartgwt.client.widgets.form.DynamicForm#saveData} is called. Note that this property can also be set by calling {@link com.smartgwt.client.widgets.form.DynamicForm#editRecord} or  {@link com.smartgwt.client.widgets.form.DynamicForm#editNewRecord}
     *
     * @param saveOperationType Operation type to use as a default. Valid values are  <code>"add"</code> or <code>"update"</code>.. Default value is null
     */
    public void setSaveOperationType(DSOperationType saveOperationType) {
        setAttribute("saveOperationType", saveOperationType.getValue(), true);
    }

    /**
     * Default {@link com.smartgwt.client.types.DSOperationType} to be performed when {@link
     * com.smartgwt.client.widgets.form.DynamicForm#saveData} is called. This property is automatically set on a call to {@link
     * com.smartgwt.client.widgets.form.DynamicForm#editRecord} or {@link
     * com.smartgwt.client.widgets.form.DynamicForm#editNewRecord}, or may be set directly via  {@link
     * com.smartgwt.client.widgets.form.DynamicForm#setSaveOperationType}. <P> If <code>saveOperationType</code> is unset, the
     * form will heuristically determine whether an "add" or "update" operation is intended based on whether the primaryKey
     * field is present and editable.
     *
     *
     * @return Returns the {@link com.smartgwt.client.types.DSOperationType} to be performed when {@link
     * com.smartgwt.client.widgets.form.DynamicForm#saveData} is called. Valid options are <code>"add"</code> or
     * <code>"update"</code>. <P> If a {@link com.smartgwt.client.data.DSRequest} configuration object is passed in containing
     * an explicit operationType this will be returned. Otherwise {@link
     * com.smartgwt.client.widgets.form.DynamicForm#getSaveOperationType saveOperationType} will be returned.
     */
    public DSOperationType getSaveOperationType()  {
        return EnumUtil.getEnum(DSOperationType.values(), getAttribute("saveOperationType"));
    }

    // ********************* Methods ***********************







    /**
     * Validate the current set of values for this values manager against validators defined in the member forms. For databound
     * valuesManagers, also perform validation against any validators defined on datasource fields. <P> Note that if validation
     * errors occur for a value that is not shown in any member forms, those errors will cause a warning and {@link
     * com.smartgwt.client.widgets.form.ValuesManager#handleHiddenValidationErrors} will be called.  This can occur if:<br> - A
     * datasource field has no correspending item in any member form<br> - The item in question is hidden<br> - The member form
     * containing the item is hidden.
     *
     * @return true if all validation passed
     */
    public native Boolean validate() /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        var retVal =self.validate();
        if(retVal == null || retVal === undefined) {
            return null;
        } else {
            return @com.smartgwt.client.util.JSOHelper::toBoolean(Z)(retVal);
        }
    }-*/;





    /**
     * Clears all errors from member forms.
     * @param showErrors If true, clear any visible error messages.
     */
    public native void clearErrors(boolean showErrors) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        self.clearErrors(showErrors);
    }-*/;

    /**
     * Clear all validation errors associated with some field in this form
     * @param fieldName field for which errors should be cleared
     * @param show if true, and the field is present in one of our member forms,                        redraw it to clear any currently
     * visible validation errors
     */
    public native void clearFieldErrors(String fieldName, boolean show) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        self.clearFieldErrors(fieldName, show);
    }-*/;



    /**
     * Are there any errors associated with any fields in this valuesManager?
     *
     * @return returns true if there are any oustanding validation errors, false                   otherwise.
     */
    public native Boolean hasErrors() /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        var retVal =self.hasErrors();
        if(retVal == null || retVal === undefined) {
            return null;
        } else {
            return @com.smartgwt.client.util.JSOHelper::toBoolean(Z)(retVal);
        }
    }-*/;

    /**
     * Are there any errors associated with a field in this valuesManager?
     * @param fieldName field to check for errors
     *
     * @return returns true if there are any oustanding validation errors, false                   otherwise.
     */
    public native Boolean hasFieldErrors(String fieldName) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        var retVal =self.hasFieldErrors(fieldName);
        if(retVal == null || retVal === undefined) {
            return null;
        } else {
            return @com.smartgwt.client.util.JSOHelper::toBoolean(Z)(retVal);
        }
    }-*/;

    /**
     * Method to explicitly show the latest set of validation errors present on this  ValuesManager.<br> Will redraw all member
     * forms to display (or clear) currently visible errors, and fire {@link
     * com.smartgwt.client.widgets.form.ValuesManager#handleHiddenValidationErrors} to allow custom handling of hidden errors.
     */
    public native void showErrors() /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        self.showErrors();
    }-*/;

    /**
     * Method to explicitly show the latest set of validation errors present on some field  within this ValuesManager.<br> If
     * the field in question is present as a visible item in a member form, the form item will be redrawn to display the error
     * message(s). Otherwise {@link com.smartgwt.client.widgets.form.ValuesManager#handleHiddenValidationErrors} will be fired
     * to allow  custom handling of hidden errors.
     */
    public native void showFieldErrors() /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        self.showFieldErrors();
    }-*/;



    /**
     * Clear out all the values managed by this values manager.
     */
    public native void clearValues() /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        self.clearValues();
    }-*/;




    /**
     * Same as {@link com.smartgwt.client.widgets.form.DynamicForm#reset}.
     */
    public native void resetValues() /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        self.resetValues();
    }-*/;

    /**
     * Compares the current set of values with the values stored by the call to the          <code>rememberValues()</code>
     * method. Returns true if the values have changed, and false          otherwise.
     *
     * @return true if current values do not match remembered values
     */
    public native Boolean valuesHaveChanged() /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        var retVal =self.valuesHaveChanged();
        if(retVal == null || retVal === undefined) {
            return null;
        } else {
            return @com.smartgwt.client.util.JSOHelper::toBoolean(Z)(retVal);
        }
    }-*/;



    /**
     * Clear the value for some field.
     * @param fieldName Which field to set the value for
     */
    public native void clearValue(String fieldName) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        self.clearValue(fieldName);
    }-*/;









    /**
     * Returns true if {@link com.smartgwt.client.widgets.form.ValuesManager#getSaveOperationType saveOperationType} is
     * currently "add".  See {@link com.smartgwt.client.widgets.form.ValuesManager#getSaveOperationType saveOperationType}.
     *
     * @return whether this form will use an "add" operation when saving
     */
    public native Boolean isNewRecord() /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        var retVal =self.isNewRecord();
        if(retVal == null || retVal === undefined) {
            return null;
        } else {
            return @com.smartgwt.client.util.JSOHelper::toBoolean(Z)(retVal);
        }
    }-*/;





    /**
     * &#010 This method exists for clean integration with existing server frameworks that have a 'cancel'&#010 feature which
     * typically clears session state associated with the form.  When this method is&#010 called, an RPC is sent to the server
     * with a parameter named&#010 {@link com.smartgwt.client.widgets.form.DynamicForm#getCancelParamName cancelParamName} with
     * the value&#010 {@link com.smartgwt.client.widgets.form.DynamicForm#getCancelParamValue cancelParamValue}.<p>&#010&#010
     * Note that no other form data is sent.  By default the current top-level page is replaced with the&#010 reply.  If you
     * wish to ignore the server reply instead, call this method like this:&#010 <pre>&#010
     * dynamicFormInstance.cancel({ignoreTimeout: true, target: null});&#010 </pre>&#010&#010
     */
    public native void cancel() /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        self.cancel();
    }-*/;

    /**
     * &#010 This method exists for clean integration with existing server frameworks that have a 'cancel'&#010 feature which
     * typically clears session state associated with the form.  When this method is&#010 called, an RPC is sent to the server
     * with a parameter named&#010 {@link com.smartgwt.client.widgets.form.DynamicForm#getCancelParamName cancelParamName} with
     * the value&#010 {@link com.smartgwt.client.widgets.form.DynamicForm#getCancelParamValue cancelParamValue}.<p>&#010&#010
     * Note that no other form data is sent.  By default the current top-level page is replaced with the&#010 reply.  If you
     * wish to ignore the server reply instead, call this method like this:&#010 <pre>&#010
     * dynamicFormInstance.cancel({ignoreTimeout: true, target: null});&#010 </pre>&#010&#010
     * @param requestProperties additional properties to set on the RPCRequest                                          that will be issued
     */
    public native void cancel(DSRequest requestProperties) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        self.cancel(requestProperties.@com.smartgwt.client.core.DataClass::getJsObj()());
    }-*/;



    // ********************* Static Methods ***********************





    /**
     * The DataSource that this component should bind to for default fields and for performing&#010 DataSource
     * requests<P>
     *
     * @param dataSource dataSource Default value is null
     */
    public void setDataSource(DataSource dataSource) {
        setAttribute("dataSource", dataSource.getOrCreateJsObj(), true);
    }

    /**
     * The DataSource that this component should bind to for default fields and for performing {@link com.smartgwt.client.data.DSRequest}. <P> Can be specified as either a DataSource instance or the String ID of a DataSource.
     *
     * @return DataSource
     */
    public DataSource getDataSource() {
        return DataSource.getOrCreateRef(getAttributeAsJavaScriptObject("dataSource"));
    }

    /**
     * Set the values for this values manager. Member forms will be updated as required by this change.
     * Note that pre-existant values in other fields are cleared out by this.
     *
     * @param values the values
     */
    public void setValues(Map values) {
        setAttribute("values", values, true);
    }

    /**
     * Set the value for some field.
     *
     * @param fieldName Name of the field being updated
     * @param value     New value.
     */
    public native void setValue(String fieldName, String value) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        self.setValue(fieldName, value);
    }-*/;

    /**
     * Set the value for some field.
     *
     * @param fieldName Name of the field being updated
     * @param value     New value.
     */
    public native void setValue(String fieldName, double value) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        self.setValue(fieldName, value);
    }-*/;

    /**
     * Set the value for some field.
     *
     * @param fieldName Name of the field being updated
     * @param value     New value.
     */
    public native void setValue(String fieldName, boolean value) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        self.setValue(fieldName, value);
    }-*/;

    /**
     * Set the value for some field.
     *
     * @param fieldName Name of the field being updated
     * @param value     New value.
     */
    public native void setValue(String fieldName, JavaScriptObject value) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        self.setValue(fieldName, value);
    }-*/;

    /**
     * Set the value for some field.
     *
     * @param fieldName Name of the field being updated
     * @param value     New value.
     */
    public native void setValue(String fieldName, Map value) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        var valueJS = @com.smartgwt.client.util.JSOHelper::convertMapToJavascriptObject(Ljava/util/Map;)(value);
        self.setValue(fieldName, valueJS);
    }-*/;

    /**
     * Set the value for some field.
     *
     * @param fieldName Name of the field being updated
     * @param value     New value.
     */
    public native void setValue(String fieldName, Record value) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        var valueJS = value.@com.smartgwt.client.data.Record::getJsObj()();
        self.setValue(fieldName, valueJS);
    }-*/;

    /**
     * Set the value for some field.
     *
     * @param fieldName Name of the field being updated
     * @param value     New value.
     */
    public native void setValue(String fieldName, DataClass value) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        var valueJS = value.@com.smartgwt.client.core.DataClass::getJsObj()();
        self.setValue(fieldName, valueJS);
    }-*/;

    /**
     * Set the value for some field.
     *
     * @param fieldName Name of the field being updated
     * @param value     New value.
     */
    public native void setValue(String fieldName, Record[] value) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        var valueJS = @com.smartgwt.client.util.JSOHelper::convertToJavaScriptArray([Ljava/lang/Object;)(value);
        self.setValue(fieldName, valueJS);
    }-*/;

    /**
     * Set the value for some field.
     *
     * @param fieldName Name of the field being updated
     * @param value     New value.
     */
    public native void setValue(String fieldName, DataClass[] value) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        var valueJS = @com.smartgwt.client.util.JSOHelper::convertToJavaScriptArray([Ljava/lang/Object;)(value);
        self.setValue(fieldName, valueJS);
    }-*/;

    /**
     * Return the value as String
     *
     * @return the value
     */
    public native String getValueAsString(String fieldName) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        var val = self.getValue(fieldName);
        return val == null || val === undefined ? null : val.toString();
    }-*/;

    /**
     * Returns the current set of values for the values manager instance. This includes the values from any form managed by this manager, as well as any values explicitly
     * applied via ValuesManager.setValues().
     *
     * @return the values
     */
    public native Map getValues() /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        var value = self.getValues();
        if(value == null) return null;
        var valueJ = @com.smartgwt.client.util.JSOHelper::convertToMap(Lcom/google/gwt/core/client/JavaScriptObject;)(value);
        return valueJ;
    }-*/;

    /**
     * Edit the record selected in the specified selection component (typically a {@link
     * com.smartgwt.client.widgets.grid.ListGrid}). <P> Updates the values of this editor to match the selected record's
     * values. <P> If this form has a dataSource, then saving via {@link com.smartgwt.client.widgets.form.ValuesManager#saveData}
     * will use the  "update" operation type.
     *
     * @param selectionComponent the ListGrid or ID of a {@link com.smartgwt.client.widgets.grid.ListGrid} whose
     *                           currently selected     record(s) is/are to be edited
     */
    public native void editSelectedData(ListGrid selectionComponent) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        var selectionComponentJS = selectionComponent.@com.smartgwt.client.widgets.BaseWidget::getOrCreateJsObj()();
        self.editSelectedData(selectionComponentJS);
    }-*/;

    /**
     * &#010 Edit an existing record.  Updates this editors values to match the values of the record &#010 passed in, via {@link com.smartgwt.client.widgets.form.ValuesManager#setValues}.&#010 <P>&#010 This method will also call {@link com.smartgwt.client.widgets.form.DynamicForm#setSaveOperationType} to ensure &#010 subsequent calls to <code>saveData()</code> will use an <code>update</code> rather than&#010 an <code>add</code> operation.&#010&#010
     *
     * @param record the record to be edited as a map of field names to their corresponding values
     */
    public native void editRecord(Record record) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        self.editRecord(record.@com.smartgwt.client.core.DataClass::getJsObj()());
    }-*/;

    /**
     * Prepare to edit a new record by clearing the current set of values (or replacing them with initialValues if specified).
     * Subsequent calls to saveData() will use an add rather than an update operation.
     */
    public native void editNewRecord() /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        return self.editNewRecord();
    }-*/;

    /**
     * Prepare to edit a new record by clearing the current set of values (or replacing them with initialValues if specified).
     * Subsequent calls to saveData() will use an add rather than an update operation.
     *
     * @param initialValues initial set of values for the editor as a map of field names to their corresponding values
     */
    public native void editNewRecord(Map initialValues) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        var initialValuesJS = @com.smartgwt.client.util.JSOHelper::convertMapToJavascriptObject(Ljava/util/Map;)(initialValues);
        return self.editNewRecord(initialValuesJS);
    }-*/;

    /**
     * Add a new member form to this valuesManager. This form's values will subsequently be available through this
     * valuesManager.  <br> Note on pre-existant values: If the valuesManager has a value specified for some field, for
     * which the member form has an item, this value will be applied to the member form.  This is true whether the item
     * has a value or not.<br> However if the member form has a value for some field, and the ValuesManager does not
     * have a specified value for the same field, we allow the valuesManager to pick up the  value from the member
     * form.
     *
     * @param member form (or ID of form) to add to                                           this valuesManager as a
     *               member.
     */
    public native void addMember(DynamicForm member) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        var memberJS = member.@com.smartgwt.client.widgets.BaseWidget::getOrCreateJsObj()();
        self.addMember(memberJS);
    }-*/;

    /**
     * Remove a member form from this valuesManager, so its values are no longer managed by this instance. This does not
     * clear the values associated with the form from the valuesManager - they will still be available via
     * valuesManager.getValues(), but will not be updated as the form is manipulated.
     *
     * @param member form to remove from this valuesManager
     */
    public native void removeMember(DynamicForm member) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        var memberJS = member.@com.smartgwt.client.widgets.BaseWidget::getOrCreateJsObj()();
        self.removeMember(memberJS);
    }-*/;

    /**
     * Returns an array of members in this ValuesManager.
     *
     * @return the members
     */
    public DynamicForm[] getMembers() {
        return convertToDynamicFormArray(getAttributeAsJavaScriptObject("members"));
    }

    private static DynamicForm[] convertToDynamicFormArray(JavaScriptObject nativeArray) {
        if (nativeArray == null) {
            return new DynamicForm[]{};
        }
        JavaScriptObject[] componentsj = JSOHelper.toArray(nativeArray);
        DynamicForm[] objects = new DynamicForm[componentsj.length];
        for (int i = 0; i < componentsj.length; i++) {
            JavaScriptObject componentJS = componentsj[i];
            DynamicForm obj = DynamicForm.getOrCreateRef(componentJS);
            objects[i] = obj;
        }
        return objects;
    }

    /**
     * Remove a member form from this valuesManager, so its values are no longer managed by this instance. This does not
     * clear the values associated with the form from the valuesManager - they will still be available via
     * valuesManager.getValues(), but will not be updated as the form is manipulated.
     *
     * @param formID ID of the form to remove from this valuesManager
     */
    public native void removeMember(String formID) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        self.removeMember(formID);
    }-*/;

    public native void fetchData() /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        self.fetchData();
    }-*/;

    /**
     * Retrieve data that matches the provided criteria, and edit the first record returned
     *
     * @param criteria search criteria
     */
    public native void fetchData(Criteria criteria) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        self.fetchData(criteria == null ? null : criteria.@com.smartgwt.client.data.Criteria::getJsObj()());
    }-*/;

    /**
     * Retrieve data that matches the provided criteria, and edit the first record returned
     *
     * @param criteria search criteria
     * @param callback callback to invoke on completion
     */
    public native void fetchData(Criteria criteria, DSCallback callback) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        var critJS = criteria == null ? null : criteria.@com.smartgwt.client.data.Criteria::getJsObj()();
        self.fetchData(critJS, function (dsResponse, data, dsRequest) {
            var responseJ = @com.smartgwt.client.data.DSResponse::new(Lcom/google/gwt/core/client/JavaScriptObject;)(dsResponse);
            var requestJ = @com.smartgwt.client.data.DSRequest::new(Lcom/google/gwt/core/client/JavaScriptObject;)(dsRequest);
            if(callback != null) callback.@com.smartgwt.client.data.DSCallback::execute(Lcom/smartgwt/client/data/DSResponse;Ljava/lang/Object;Lcom/smartgwt/client/data/DSRequest;)(responseJ, data, requestJ);
        });
    }-*/;

    /**
     * Retrieve data that matches the provided criteria, and edit the first record returned
     *
     * @param criteria          search criteria
     * @param callback          callback to invoke on completion
     * @param requestProperties additional properties to set on the DSRequest
     *                          that will be issued
     */
    public native void fetchData(Criteria criteria, DSCallback callback, DSRequest requestProperties) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        var critJS = criteria == null ? null : criteria.@com.smartgwt.client.data.Criteria::getJsObj()();
        var requestPropertiesJS = requestProperties == null ? null : requestProperties.@com.smartgwt.client.core.DataClass::getJsObj()();
        self.fetchData(critJS, function (dsResponse, data, dsRequest) {
            var responseJ = @com.smartgwt.client.data.DSResponse::new(Lcom/google/gwt/core/client/JavaScriptObject;)(dsResponse);
            var requestJ = @com.smartgwt.client.data.DSRequest::new(Lcom/google/gwt/core/client/JavaScriptObject;)(dsRequest);
            if(callback != null) callback.@com.smartgwt.client.data.DSCallback::execute(Lcom/smartgwt/client/data/DSResponse;Ljava/lang/Object;Lcom/smartgwt/client/data/DSRequest;)(responseJ, data, requestJ);
        }, requestPropertiesJS);
    }-*/;

    /**
     * Retrieve data that matches the provided criteria, and edit the first record returned.<br> Differs from {@link
     * com.smartgwt.client.widgets.form.DynamicForm#fetchData} in that a case insensitive substring match will be
     * performed against the criteria to retrieve the data.
     *
     * @param criteria search criteria
     */
    public native void filterData(Criteria criteria) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        self.filterData(criteria == null ? null : criteria.@com.smartgwt.client.data.Criteria::getJsObj()());
    }-*/;

    /**
     * Retrieve data that matches the provided criteria, and edit the first record returned.<br> Differs from {@link
     * com.smartgwt.client.widgets.form.DynamicForm#fetchData} in that a case insensitive substring match will be
     * performed against the criteria to retrieve the data.
     *
     * @param criteria search criteria
     * @param callback callback to invoke on completion
     */
    public native void filterData(Criteria criteria, DSCallback callback) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        var critJS = criteria == null ? null : criteria.@com.smartgwt.client.data.Criteria::getJsObj()();
        self.filterData(critJS, function (dsResponse, data, dsRequest) {
            var responseJ = @com.smartgwt.client.data.DSResponse::new(Lcom/google/gwt/core/client/JavaScriptObject;)(dsResponse);
            var requestJ = @com.smartgwt.client.data.DSRequest::new(Lcom/google/gwt/core/client/JavaScriptObject;)(dsRequest);
            if(callback != null) callback.@com.smartgwt.client.data.DSCallback::execute(Lcom/smartgwt/client/data/DSResponse;Ljava/lang/Object;Lcom/smartgwt/client/data/DSRequest;)(responseJ, data, requestJ);
        });
    }-*/;

    /**
     * Retrieve data that matches the provided criteria, and edit the first record returned.<br> Differs from {@link
     * com.smartgwt.client.widgets.form.DynamicForm#fetchData} in that a case insensitive substring match will be
     * performed against the criteria to retrieve the data.
     *
     * @param criteria          search criteria
     * @param callback          callback to invoke on completion
     * @param requestProperties additional properties to set on the DSRequest
     *                          that will be issued
     */
    public native void filterData(Criteria criteria, DSCallback callback, DSRequest requestProperties) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        var critJS = criteria == null ? null : criteria.@com.smartgwt.client.data.Criteria::getJsObj()();
        var requestPropertiesJS = requestProperties == null ? null : requestProperties.@com.smartgwt.client.core.DataClass::getJsObj()();
        self.filterData(critJS, function (dsResponse, data, dsRequest) {
            var responseJ = @com.smartgwt.client.data.DSResponse::new(Lcom/google/gwt/core/client/JavaScriptObject;)(dsResponse);
            var requestJ = @com.smartgwt.client.data.DSRequest::new(Lcom/google/gwt/core/client/JavaScriptObject;)(dsRequest);
            if(callback != null) callback.@com.smartgwt.client.data.DSCallback::execute(Lcom/smartgwt/client/data/DSResponse;Ljava/lang/Object;Lcom/smartgwt/client/data/DSRequest;)(responseJ, data, requestJ);
        }, requestPropertiesJS);
    }-*/;

    /**
     * Validate and then save the form's current values to the {@link com.smartgwt.client.data.DataSource} this form is
     * bound to. <p> If client-side validators are defined, they are executed first, and if any errors are found the
     * save is aborted and the form will show the errors. <p> If client-side validation passes, a {@link
     * com.smartgwt.client.data.DSRequest} will be sent, exactly as though {@link com.smartgwt.client.data.DataSource#addData}
     * or {@link com.smartgwt.client.data.DataSource#updateData} had been called with  {@link
     * com.smartgwt.client.widgets.form.DynamicForm#getValues} as data.  The {@link
     * com.smartgwt.client.data.DSRequest#getOperationType operationType} will be either "update" or "add" depending on
     * whether values were initially provided via <code>editRecord()</code> or <code>editNew()</code>. <P> On either a
     * client-side or server-side validation failure, validation errors will be displayed in the form.  Visible items
     * within a DynamicForms will be redrawn to display errors. Validation failure occuring on hidden items, or
     * DataSource fields with no  associated form items may be handled via {@link com.smartgwt.client.widgets.form.DynamicForm#handleHiddenValidationErrors}
     * or {@link com.smartgwt.client.widgets.form.ValuesManager#handleHiddenValidationErrors}. <P> {@link
     * com.smartgwt.client.widgets.form.DynamicForm#getValidationURL validationURL} can be set to do validation against
     * a different URL from where the form will ultimately save, as part of an incremental upgrade strategy for Struts
     * and Struts-like applications.
     */
    public native void saveData() /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        self.saveData();
    }-*/;

    /**
     * Validate and then save the form's current values to the {@link com.smartgwt.client.data.DataSource} this form is
     * bound to. <p> If client-side validators are defined, they are executed first, and if any errors are found the
     * save is aborted and the form will show the errors. <p> If client-side validation passes, a {@link
     * com.smartgwt.client.data.DSRequest} will be sent, exactly as though {@link com.smartgwt.client.data.DataSource#addData}
     * or {@link com.smartgwt.client.data.DataSource#updateData} had been called with  {@link
     * com.smartgwt.client.widgets.form.DynamicForm#getValues} as data.  The {@link
     * com.smartgwt.client.data.DSRequest#getOperationType operationType} will be either "update" or "add" depending on
     * whether values were initially provided via <code>editRecord()</code> or <code>editNew()</code>. <P> On either a
     * client-side or server-side validation failure, validation errors will be displayed in the form.  Visible items
     * within a DynamicForms will be redrawn to display errors. Validation failure occuring on hidden items, or
     * DataSource fields with no  associated form items may be handled via {@link com.smartgwt.client.widgets.form.DynamicForm#handleHiddenValidationErrors}
     * or {@link com.smartgwt.client.widgets.form.ValuesManager#handleHiddenValidationErrors}. <P> {@link
     * com.smartgwt.client.widgets.form.DynamicForm#getValidationURL validationURL} can be set to do validation against
     * a different URL from where the form will ultimately save, as part of an incremental upgrade strategy for Struts
     * and Struts-like applications.
     *
     * @param callback callback to invoke on completion
     */
    public native void saveData(DSCallback callback) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        self.saveData(function (dsResponse, data, dsRequest) {
            var responseJ = @com.smartgwt.client.data.DSResponse::new(Lcom/google/gwt/core/client/JavaScriptObject;)(dsResponse);
            var requestJ = @com.smartgwt.client.data.DSRequest::new(Lcom/google/gwt/core/client/JavaScriptObject;)(dsRequest);
            if(callback != null) callback.@com.smartgwt.client.data.DSCallback::execute(Lcom/smartgwt/client/data/DSResponse;Ljava/lang/Object;Lcom/smartgwt/client/data/DSRequest;)(responseJ, data, requestJ);
        });
    }-*/;

    /**
     * Validate and then save the form's current values to the {@link com.smartgwt.client.data.DataSource} this form is
     * bound to. <p> If client-side validators are defined, they are executed first, and if any errors are found the
     * save is aborted and the form will show the errors. <p> If client-side validation passes, a {@link
     * com.smartgwt.client.data.DSRequest} will be sent, exactly as though {@link com.smartgwt.client.data.DataSource#addData}
     * or {@link com.smartgwt.client.data.DataSource#updateData} had been called with  {@link
     * com.smartgwt.client.widgets.form.DynamicForm#getValues} as data.  The {@link
     * com.smartgwt.client.data.DSRequest#getOperationType operationType} will be either "update" or "add" depending on
     * whether values were initially provided via <code>editRecord()</code> or <code>editNew()</code>. <P> On either a
     * client-side or server-side validation failure, validation errors will be displayed in the form.  Visible items
     * within a DynamicForms will be redrawn to display errors. Validation failure occuring on hidden items, or
     * DataSource fields with no  associated form items may be handled via {@link com.smartgwt.client.widgets.form.DynamicForm#handleHiddenValidationErrors}
     * or {@link com.smartgwt.client.widgets.form.ValuesManager#handleHiddenValidationErrors}. <P> {@link
     * com.smartgwt.client.widgets.form.DynamicForm#getValidationURL validationURL} can be set to do validation against
     * a different URL from where the form will ultimately save, as part of an incremental upgrade strategy for Struts
     * and Struts-like applications.
     *
     * @param callback          callback to invoke on completion
     * @param requestProperties additional properties to set on the DSRequest
     *                          that will be issued
     */
    public native void saveData(DSCallback callback, DSRequest requestProperties) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        var requestPropertiesJS = requestProperties == null ? null : requestProperties.@com.smartgwt.client.core.DataClass::getJsObj()();
        self.saveData(function (dsResponse, data, dsRequest) {
            var responseJ = @com.smartgwt.client.data.DSResponse::new(Lcom/google/gwt/core/client/JavaScriptObject;)(dsResponse);
            var requestJ = @com.smartgwt.client.data.DSRequest::new(Lcom/google/gwt/core/client/JavaScriptObject;)(dsRequest);
            if(callback != null) callback.@com.smartgwt.client.data.DSCallback::execute(Lcom/smartgwt/client/data/DSResponse;Ljava/lang/Object;Lcom/smartgwt/client/data/DSRequest;)(responseJ, data, requestJ);
        }, requestPropertiesJS);
    }-*/;

    /**
     * <code>submit()</code> is automatically called when a {@link com.smartgwt.client.widgets.form.fields.SubmitItem}
     * included in the form is clicked, or, if {@link com.smartgwt.client.widgets.form.DynamicForm#getSaveOnEnter
     * saveOnEnter} is set, when the "Enter" key is pressed in a text input.  Submit can also be manually called. <P> If
     * {@link com.smartgwt.client.widgets.form.DynamicForm#submitValues} exists, it will be called, then immediately
     * return. <P> Otherwise, default behavior varies based on {@link com.smartgwt.client.widgets.form.DynamicForm#getCanSubmit
     * canSubmit}: if <code>canSubmit</code> is false, {@link com.smartgwt.client.widgets.form.DynamicForm#saveData}
     * will be called to handle saving via Smart GWT databinding.   <P> If <code>canSubmit</code> is true, the form
     * will be submitted like an ordinary HTML form via {@link com.smartgwt.client.widgets.form.DynamicForm#submitForm}.
     * <P> The parameters to <code>submit()</code> apply only if <code>submit()</code> will be calling {@link
     * com.smartgwt.client.widgets.form.ValuesManager#saveData}.  If you override <code>submit()</code>, you can safely
     * ignore the parameters as Smart GWT framework code does not pass them.
     */
    public native void submit() /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        self.submit();
    }-*/;

    /**
     * <code>submit()</code> is automatically called when a {@link com.smartgwt.client.widgets.form.fields.SubmitItem}
     * included in the form is clicked, or, if {@link com.smartgwt.client.widgets.form.DynamicForm#getSaveOnEnter
     * saveOnEnter} is set, when the "Enter" key is pressed in a text input.  Submit can also be manually called. <P> If
     * {@link com.smartgwt.client.widgets.form.DynamicForm#submitValues} exists, it will be called, then immediately
     * return. <P> Otherwise, default behavior varies based on {@link com.smartgwt.client.widgets.form.DynamicForm#getCanSubmit
     * canSubmit}: if <code>canSubmit</code> is false, {@link com.smartgwt.client.widgets.form.DynamicForm#saveData}
     * will be called to handle saving via Smart GWT databinding.   <P> If <code>canSubmit</code> is true, the form
     * will be submitted like an ordinary HTML form via {@link com.smartgwt.client.widgets.form.DynamicForm#submitForm}.
     * <P> The parameters to <code>submit()</code> apply only if <code>submit()</code> will be calling {@link
     * com.smartgwt.client.widgets.form.ValuesManager#saveData}.  If you override <code>submit()</code>, you can safely
     * ignore the parameters as Smart GWT framework code does not pass them.
     *
     * @param callback callback to invoke on completion.                                          [Ignored if
     *                 this.canSubmit is true]
     */
    public native void submit(DSCallback callback) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        self.submit(function (dsResponse, data, dsRequest) {
            var responseJ = @com.smartgwt.client.data.DSResponse::new(Lcom/google/gwt/core/client/JavaScriptObject;)(dsResponse);
            var requestJ = @com.smartgwt.client.data.DSRequest::new(Lcom/google/gwt/core/client/JavaScriptObject;)(dsRequest);
            if(callback != null) callback.@com.smartgwt.client.data.DSCallback::execute(Lcom/smartgwt/client/data/DSResponse;Ljava/lang/Object;Lcom/smartgwt/client/data/DSRequest;)(responseJ, data, requestJ);
        });
    }-*/;

    /**
     * <code>submit()</code> is automatically called when a {@link com.smartgwt.client.widgets.form.fields.SubmitItem}
     * included in the form is clicked, or, if {@link com.smartgwt.client.widgets.form.DynamicForm#getSaveOnEnter
     * saveOnEnter} is set, when the "Enter" key is pressed in a text input.  Submit can also be manually called. <P> If
     * {@link com.smartgwt.client.widgets.form.DynamicForm#submitValues} exists, it will be called, then immediately
     * return. <P> Otherwise, default behavior varies based on {@link com.smartgwt.client.widgets.form.DynamicForm#getCanSubmit
     * canSubmit}: if <code>canSubmit</code> is false, {@link com.smartgwt.client.widgets.form.DynamicForm#saveData}
     * will be called to handle saving via Smart GWT databinding.   <P> If <code>canSubmit</code> is true, the form
     * will be submitted like an ordinary HTML form via {@link com.smartgwt.client.widgets.form.DynamicForm#submitForm}.
     * <P> The parameters to <code>submit()</code> apply only if <code>submit()</code> will be calling {@link
     * com.smartgwt.client.widgets.form.ValuesManager#saveData}.  If you override <code>submit()</code>, you can safely
     * ignore the parameters as Smart GWT framework code does not pass them.
     *
     * @param callback          callback to invoke on completion.                                          [Ignored if
     *                          this.canSubmit is true]
     * @param requestProperties additional properties to set on the DSRequest
     *                          that will be issued                                          [Ignored if this.canSubmit
     *                          is true]
     */
    public native void submit(DSCallback callback, DSRequest requestProperties) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        var requestPropertiesJS = requestProperties == null ? null : requestProperties.@com.smartgwt.client.core.DataClass::getJsObj()();
        self.saveData(function (dsResponse, data, dsRequest) {
            var responseJ = @com.smartgwt.client.data.DSResponse::new(Lcom/google/gwt/core/client/JavaScriptObject;)(dsResponse);
            var requestJ = @com.smartgwt.client.data.DSRequest::new(Lcom/google/gwt/core/client/JavaScriptObject;)(dsRequest);
            if(callback != null) callback.@com.smartgwt.client.data.DSCallback::execute(Lcom/smartgwt/client/data/DSResponse;Ljava/lang/Object;Lcom/smartgwt/client/data/DSRequest;)(responseJ, data, requestJ);
        }, requestPropertiesJS);
    }-*/;

    /**
     * Make a snapshot of the current set of values, so we can reset to them later. Creates a new object, then adds all
     * non-method properties of values to the new object. Use resetValues() to revert to these values. Note that this
     * method is automatically called when the values for this form are set programmatically via a call to
     * DynamicForm.setValues().
     *
     * @return copy of current form values
     */
    public native JavaScriptObject rememberValues() /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        return self.rememberValues();
    }-*/;


    /**
     * Setter for validation errors on this form. Errors passed in should be a Map in the format
     * {fieldName1:errors, fieldName2:errors}
     * <p/>
     * Where the errors value may be either a string (single error message) or an array of strings (if multiple errors should be applied to the field in question).
     *
     * @param errors     list of errors as a map with the field names as keys
     * @param showErrors If true redraw form to display errors now. Otherwise errors can be displayed by calling
     *                   {@link ValuesManager#showErrors} <b>Note</b>: When the errors are shown, handleHiddenValidationErrors() will be fired for errors
     *                   on hidden fields, or with no associated formItem.
     */
    public native void setErrors(Map errors, boolean showErrors) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        var errorsJS = @com.smartgwt.client.util.JSOHelper::convertMapToJavascriptObject(Ljava/util/Map;)(errors);
        return self.setErrors(errorsJS, showErrors);
    }-*/;

    /**
     * Returns the set of errors for this valuesManager.
     *
     * @return errors. key is field name, value is error. Returns null if no errors
     */
    public native Map getErrors() /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        var value = self.getErrors();
        if(value == null) return null;
        var valueJ = @com.smartgwt.client.util.JSOHelper::convertToMap(Lcom/google/gwt/core/client/JavaScriptObject;)(value);
        return valueJ;
    }-*/;

    /**
     * Set field validation error for some field. The showErrors parameter allows the errors to be displayed immediately.
     * Alternatively, an explicit call to {@link DynamicForm#showFieldErrors} will display the errors for this field.
     *
     * @param fieldName  field to apply the new errors to
     * @param error      error to apply to the field in question
     * @param showErrors If true this method will fall through to DynamicForm.showFieldErrors() to update the display
     */
    public native void setFieldErrors(String fieldName, String error, boolean showErrors) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        return self.setFieldErrors(fieldName, error, showErrors);
    }-*/;

    /**
     * Set field validation errors for some field. The showErrors parameter allows the errors to be displayed immediately.
     * Alternatively, an explicit call to {@link DynamicForm#showFieldErrors} will display the errors for this field.
     *
     * @param fieldName  field to apply the new errors to
     * @param errors     errors to apply to the field in question
     * @param showErrors If true this method will fall through to DynamicForm.showFieldErrors() to update the display
     */
    public native void setFieldErrors(String fieldName, String[] errors, boolean showErrors) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        var errorsJS = @com.smartgwt.client.util.JSOHelper::convertToJavaScriptArray([Ljava/lang/Object;)(errors);
        return self.setFieldErrors(fieldName, errorsJS, showErrors);
    }-*/;

    /**
     * Returns any validation errors for some field in this valuesManager. If no errors are present, will return null.
     *
     * @param fieldName the field name
     * @return error messages for the field.
     */
    public native String[] getFieldErrors(String fieldName) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        var value = self.getFieldErrors(fieldName);
        if(value == null) return null;
        if(!@com.smartgwt.client.util.JSOHelper::isArray(Lcom/google/gwt/core/client/JavaScriptObject;)(data)) {
            value = [value];
        }
        return @com.smartgwt.client.util.JSOHelper::convertToJavaStringArray(Lcom/google/gwt/core/client/JavaScriptObject;)(value);
    }-*/;

    /**
     * Retrieve a {@link com.smartgwt.client.widgets.form.fields.FormItem} from this ValuesManager. <P> Takes a field {@link
     * com.smartgwt.client.widgets.form.fields.FormItem#getName 'name'} or ${isc.DocUtils.linkForRef('dataPath')}, and searches
     * through the members of this valuesManager for an editor for that field. If found the appropriate formItem will be
     * returned. Note that if a dataPath is passed in, it should be the full data path for the item, including any canvas level
     * {@link com.smartgwt.client.widgets.Canvas#getDataPath 'dataPath'} specified on the member form containing this form
     * item. <br>Note: Unlike the <code>DynamicForm</code> class, this method will not return an  item by index
     * @param itemID item fieldName or dataPath identifier
     *
     * @return form item for editing/displaying the appropriate field, or null if   no formItem can be found for the field.
     */
    public native FormItem getItem(String itemID) /*-{
        var self = this.@com.smartgwt.client.core.BaseClass::getOrCreateJsObj()();
        var fieldJS = self.getItem(itemID);
        return @com.smartgwt.client.widgets.form.fields.FormItemFactory::getFormItem(Lcom/google/gwt/core/client/JavaScriptObject;)(fieldJS);
    }-*/;


}



