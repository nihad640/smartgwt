/*
 * SmartGWT (GWT for SmartClient)
 * Copyright 2008 and beyond, Isomorphic Software, Inc.
 *
 * SmartGWT is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation.  SmartGWT is also
 * available under typical commercial license terms - see
 * http://smartclient.com/license
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 */
 
package com.smartgwt.client.widgets.grid.events;

import com.smartgwt.client.event.*;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

public interface HasChangeHandlers extends HasHandlers {
    /**
     * If this field is editable, any {@link com.smartgwt.client.widgets.form.fields.FormItem#addChangeHandler} handler
     * specified on the ListGridField will be passed onto the editors for this field.
     *
     * @param handler the change handler
     * @return {@link HandlerRegistration} used to remove this handler
     */
    HandlerRegistration addChangeHandler(ChangeHandler handler);
}
