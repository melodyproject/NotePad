/*
 * Copyright (c) 2015 Jonas Kalderstam.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.nononsenseapps.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

import com.nononsenseapps.helpers.Log;
import com.nononsenseapps.notepad.R;

/**
 * This class is designed to act as a simple version of the touch delegate. E.g.
 * it is intended to enlarge the touch area for a specified child view.
 * 
 * Define it entirely in XML as the following example demonstrates:
 * 
 * <com.nononsenseapps.ui.DelegateFrame
        xmlns:nononsenseapps="http://nononsenseapps.com"
        android:id="@+id/datecheckcontainer"
        android:layout_width="wrap_content"
        android:layout_height="fill_parent"
        android:minWidth="44dp"
        android:paddingBottom="4dp"
        android:paddingLeft="8dp"
        android:paddingRight="4dp"
        android:paddingTop="8dp"
        android:clickable="true"
        nononsenseapps:enlargedView="@+id/itemDone" >
        
        It's important to add android:clickable="true" and 
        nononsenseapps:enlargedView="@+id/YOURIDHERE"
 *
 */
public class DelegateFrame extends RelativeLayout implements OnClickListener {
    private static final int UNDEFINED = -1;
    private final int enlargedViewId;
    private View cachedView;

	public DelegateFrame(Context context) {
		this(context, null);
	}

	public DelegateFrame(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public DelegateFrame(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DelegateFrame);
        Log.d("delegate", "setting xml values! pre");

        enlargedViewId = a.getResourceId(R.styleable.DelegateFrame_enlargedView, UNDEFINED);
        // enlargedViewId = attrs.getAttributeResourceValue(NONONSENSEAPPSNS, ATTR_ENLARGEDVIEW, UNDEFINED);
        Log.d("delegate", "setting xml values! view: " + enlargedViewId);
        setOnClickListener(this);

        a.recycle();
	}

	@Override
	public void onClick(View v) {
        if (cachedView == null && enlargedViewId != UNDEFINED) {
            cachedView = findViewById(enlargedViewId);
        }
		if (cachedView != null) {
            cachedView.performClick();
		}
	}
}
