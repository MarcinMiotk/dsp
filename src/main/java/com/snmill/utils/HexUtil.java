package com.snmill.utils;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

public class HexUtil {

    public static String toHex(byte[] array) {
        return new HexBinaryAdapter().marshal(array).toLowerCase();
    }

    public static byte[] toBytes(String hex) {
        return new HexBinaryAdapter().unmarshal(hex);
    }
}
