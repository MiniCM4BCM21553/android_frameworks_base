/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.scenegraph;

import java.lang.Math;
import java.util.ArrayList;

import android.renderscript.RenderScriptGL;
import android.renderscript.Float4;
import android.renderscript.Matrix4f;
import android.renderscript.ProgramFragment;
import android.renderscript.ProgramStore;
import android.renderscript.ProgramVertex;
import android.renderscript.Element;
import android.util.Log;

/**
 * @hide
 */
public class Float4Param extends ShaderParam {

    Float4 mValue;
    Camera mCamera;
    LightBase mLight;
    int mVecSize;

    public Float4Param(String name) {
        super(name);
        mValue = new Float4();
    }

    public void setValue(Float4 v) {
        mValue = v;
    }

    public Float4 getValue() {
        return mValue;
    }

    public void setVecSize(int vecSize) {
        mVecSize = vecSize;
    }

    public void setCamera(Camera c) {
        mCamera = c;
    }

    public void setLight(LightBase l) {
        mLight = l;
    }

    int getTypeFromName() {
        int paramType = FLOAT4_DATA;
        if (mParamName.equalsIgnoreCase(cameraPos)) {
            paramType = FLOAT4_CAMERA_POS;
        } else if(mParamName.equalsIgnoreCase(cameraDir)) {
            paramType = FLOAT4_CAMERA_DIR;
        } else if(mParamName.equalsIgnoreCase(lightColor)) {
            paramType = FLOAT4_LIGHT_COLOR;
        } else if(mParamName.equalsIgnoreCase(lightPos)) {
            paramType = FLOAT4_LIGHT_POS;
        } else if(mParamName.equalsIgnoreCase(lightDir)) {
            paramType = FLOAT4_LIGHT_DIR;
        }
        return paramType;
    }

    void initLocalData(RenderScriptGL rs) {
        mRsFieldItem.type = getTypeFromName();
        mRsFieldItem.bufferOffset = mOffset;
        mRsFieldItem.float_value = mValue;
        mRsFieldItem.float_vecSize = mVecSize;
        if (mCamera != null) {
            mRsFieldItem.camera = mCamera.getRSData(rs).getAllocation();
        }
        if (mLight != null) {
            mRsFieldItem.light = mLight.getRSData(rs).getAllocation();
        }
    }
}




