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
 
package com.smartgwt.client.widgets.form.fields;



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
import com.smartgwt.client.util.*;
import com.google.gwt.event.shared.*;
import com.google.gwt.event.shared.HasHandlers;

/**
 * Provides a compact interface for editing a date range, by providing a read-only display of  the current selected date
 * range with an icon to launch a DateRangeDialog to edit the  range.
 */
public class MiniDateRangeItem extends StaticTextItem {

    public static MiniDateRangeItem getOrCreateRef(JavaScriptObject jsObj) {
        if(jsObj == null) return null;
        RefDataClass obj = RefDataClass.getRef(jsObj);
        if(obj != null) {
            obj.setJsObj(jsObj);
            return (MiniDateRangeItem) obj;
        } else {
            return new MiniDateRangeItem(jsObj);
        }
    }

    public MiniDateRangeItem(){
        setType("MiniDateRangeItem");
    }

    public MiniDateRangeItem(JavaScriptObject jsObj){
        super(jsObj);
    }

    public MiniDateRangeItem(String name) {
        setName(name);
        setType("MiniDateRangeItem");
    }

    public MiniDateRangeItem(String name, String title) {
        setName(name);
		setTitle(title);
        setType("MiniDateRangeItem");
    }

    // ********************* Properties / Attributes ***********************

    /**
     * Icon that launches a {@link com.smartgwt.client.widgets.DateChooser} for choosing an absolute date.
     *
     * @param pickerIcon pickerIcon Default value is null
     */
    public void setPickerIcon(FormItemIcon pickerIcon) {
        setAttribute("pickerIcon", pickerIcon.getJsObj());
    }

    /**
     * Icon that launches a {@link com.smartgwt.client.widgets.DateChooser} for choosing an absolute date.
     *
     *
     * @return FormItemIcon
     */
    public FormItemIcon getPickerIcon()  {
        return new FormItemIcon(getAttributeAsJavaScriptObject("pickerIcon"));
    }

    /**
     * The prompt to show when the mouse is hovered over the {@link
     * com.smartgwt.client.widgets.form.fields.MiniDateRangeItem#getPickerIcon pickerIcon}.
     *
     * @param pickerIconPrompt pickerIconPrompt Default value is "Show Date Chooser"
     */
    public void setPickerIconPrompt(String pickerIconPrompt) {
        setAttribute("pickerIconPrompt", pickerIconPrompt);
    }

    /**
     * The prompt to show when the mouse is hovered over the {@link
     * com.smartgwt.client.widgets.form.fields.MiniDateRangeItem#getPickerIcon pickerIcon}.
     *
     *
     * @return String
     */
    public String getPickerIconPrompt()  {
        return getAttributeAsString("pickerIconPrompt");
    }

    /**
     * Allow miniDateRangeItems' values to show up in the form's values array, or if  {@link
     * com.smartgwt.client.widgets.form.DynamicForm#getValuesAsCriteria DynamicForm.getValuesAsCriteria} is called, for the
     * criterion to be included in the returned AdvancedCriteria object
     *
     * @param shouldSaveValue shouldSaveValue Default value is true
     */
    public void setShouldSaveValue(Boolean shouldSaveValue) {
        setAttribute("shouldSaveValue", shouldSaveValue);
    }

    /**
     * Allow miniDateRangeItems' values to show up in the form's values array, or if  {@link
     * com.smartgwt.client.widgets.form.DynamicForm#getValuesAsCriteria DynamicForm.getValuesAsCriteria} is called, for the
     * criterion to be included in the returned AdvancedCriteria object
     *
     *
     * @return Boolean
     */
    public Boolean getShouldSaveValue()  {
        return getAttributeAsBoolean("shouldSaveValue");
    }

    // ********************* Methods ***********************
            
    /**
     * Whether the DateRangeDialog opened when the  {@link
     * com.smartgwt.client.widgets.form.fields.MiniDateRangeItem#getPickerIcon pickerIcon} is clicked should display 
     * RelativeDateItems or {@link com.smartgwt.client.widgets.form.fields.DateItem}s.
     *
     * @return true
     */
    public native Boolean allowRelativeDates() /*-{
        var self = this.@com.smartgwt.client.core.DataClass::getJsObj()();
        var retVal =self.allowRelativeDates();
        if(retVal == null || retVal === undefined) {
            return null;
        } else {
            return @com.smartgwt.client.util.JSOHelper::toBoolean(Z)(retVal);
        }
    }-*/;
            
    /**
     * Returns the Criterion entered in the fields shown in the  {@link
     * com.smartgwt.client.widgets.form.fields.MiniDateRangeItem#getRangeDialog rangeDialog}. <P> If both dates are entered, a
     * Criterion with an "and" {@link com.smartgwt.client.types.OperatorId operator} will be returned with both a "greaterThan"
     * and "lessThan" sub-criteria.  If either date is omitted, only the "greaterThan" (from date) or "lessThan" (to date)
     * Criterion is returned.
     *
     * @return 
     * @see com.smartgwt.client.docs.CriteriaEditing CriteriaEditing overview and related methods
     */
    public native Criterion getCriterion() /*-{
        var self = this.@com.smartgwt.client.core.DataClass::getJsObj()();
        var ret = self.getCriterion();
        if(ret == null || ret === undefined) return null;
        return @com.smartgwt.client.data.Criterion::new(Lcom/google/gwt/core/client/JavaScriptObject;)(ret);
    }-*/;

    // ********************* Static Methods ***********************


    /**
     * Initial value for the "from" date.
     * Sets the {@link DateRangeItem#getFromDate fromDate} for this DateRangeItem.
     *
     * @param fromDate the date from which this item should start it's range. Default value is today
     */
    public void setFromDate(java.util.Date fromDate) {
        setAttribute("fromDate", fromDate);
    }

    /**
     * Initial value for the "from" date.
     *
     *
     * @return java.util.Date
     */
    public java.util.Date getFromDate()  {
        return getAttributeAsDate("fromDate");
    }

    /**
     * Initial value for the "to" date.
     * Sets the {@link DateRangeItem#getToDate toDate} for this DateRangeItem.
     *
     * @param toDate the date at which this item should end it's range. Default value is today
     */
    public void setToDate(java.util.Date toDate) {
        setAttribute("toDate", toDate);
    }

    /**
     * Initial value for the "to" date.
     *
     *
     * @return java.util.Date
     */
    public java.util.Date getToDate()  {
        return getAttributeAsDate("toDate");
    }

    /**
     * Retrieves the current value of this dateRangeItem.  The return value is a
     * ${isc.DocUtils.linkForRef('object:DateRange')} object that excludes start and end values if they aren't set.
     *
     * @return the current value of this item
     */
    public native DateRange getValue() /*-{
        var self = this.@com.smartgwt.client.core.DataClass::getJsObj()();
        var valueJS = self.getValue();
        if(valueJS == null) return null;
        var startJS = valueJS.start;
        var endJS = valueJS.end;
        var start = startJS == null || startJS === undefined ? null : @com.smartgwt.client.util.JSOHelper::toDate(D)(startJS.getTime());
        var end = endJS == null || endJS === undefined ? null : @com.smartgwt.client.util.JSOHelper::toDate(D)(endJS.getTime());
        return @com.smartgwt.client.widgets.form.fields.DateRange::new(Ljava/util/Date;Ljava/util/Date;)(start, end);
    }-*/;

    /**
     * Sets the value for this dateRangeItem.  The value parameter is a  {@link DateRange DateRange} object
     * that optionally includes both start and end values.
     *
     * @param value the new value for this item
     */
    public native void setValue(DateRange value) /*-{
        var self = this.@com.smartgwt.client.core.DataClass::getJsObj()();
        var start = value.@com.smartgwt.client.widgets.form.fields.DateRange::getStart()();
        var end = value.@com.smartgwt.client.widgets.form.fields.DateRange::getEnd()();
        var startJS = start == null ? null : @com.smartgwt.client.util.JSOHelper::toDateJS(Ljava/util/Date;)(start);
        var endJS = end == null ? null : @com.smartgwt.client.util.JSOHelper::toDateJS(Ljava/util/Date;)(end);
        var valueJS = {start:startJS, end:endJS};
        self.setValue(valueJS);
    }-*/;

}



