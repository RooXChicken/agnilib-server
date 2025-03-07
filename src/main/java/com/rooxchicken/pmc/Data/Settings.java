package com.rooxchicken.pmc.Data;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class Settings
{
    public static final String SETTINGS_PATH = "pmc.cfg";

    public static void saveData(String _path, Object _value)
    {
        File _file = new File(SETTINGS_PATH);

        HashMap<String, Object> moduleSettings = new HashMap<String, Object>();
		moduleSettings.put(_path, _value);

		// _file.addProperty(_path, new Gson().toJson(moduleSettings));
    }
}
