package com.example.fwprld.ui.broadcastStream;

import android.webkit.URLUtil;

public class OpenTokConfig {
    // *** Fill the following variables using your own Project info from the OpenTok dashboard  ***
    // ***                      https://dashboard.tokbox.com/projects                           ***


    // Replace with your OpenTok API key
    public static final String API_KEY = "46339222";
    // Replace with a generated Session ID
//    public static final String SESSION_ID = "2_MX40NjMzOTIyMn5-MTU1OTU0MzA2NzU2NX43T3VKdmVoY0w0eDhuanhyS3I0TnNPT3F-fg";
//    // Replace with a generated token (from the dashboard or using an OpenTok server SDK)
//    public static final String TOKEN = "T1==cGFydG5lcl9pZD00NjMzOTIyMiZzaWc9YWU5ODExYmQ3MmE0MDYwZWI5ZTE1M2MxMzU5YTg4MGM3MDQ0YzllZjpzZXNzaW9uX2lkPTJfTVg0ME5qTXpPVEl5TW41LU1UVTFPVFUwTXpBMk56VTJOWDQzVDNWS2RtVm9ZMHcwZURodWFuaHlTM0kwVG5OUFQzRi1mZyZjcmVhdGVfdGltZT0xNTU5NTQzMDY3JnJvbGU9bW9kZXJhdG9yJm5vbmNlPTE1NTk1NDMwNjcuNTg3NDY1NDE0ODE2MA==";


    public static final String SESSION_ID = "1_MX40NjMzOTIyMn5-MTU2MDgzNjU2NTE1Mn4yT3pmYy85ZFJiMkptYUc1UW50QitjcDF-fg";
    public static final String TOKEN = "T1==cGFydG5lcl9pZD00NjMzOTIyMiZzaWc9ZTU2NDFjNjc5MTZlNDY2MmVhZTU2M2IzOGQ2YWU1MWRiYTkwZjdmNTpzZXNzaW9uX2lkPTFfTVg0ME5qTXpPVEl5TW41LU1UVTJNRGd6TmpVMk5URTFNbjR5VDNwbVl5ODVaRkppTWtwdFlVYzFVVzUwUWl0amNERi1mZyZjcmVhdGVfdGltZT0xNTYwODM2NTY1JnJvbGU9bW9kZXJhdG9yJm5vbmNlPTE1NjA4MzY1NjUuMTY3NjE0NTc5NDU3Mg==";




    /*                           ***** OPTIONAL *****
     If you have set up a server to provide session information replace the null value
     in CHAT_SERVER_URL with it.

     For example: "https://yoursubdomain.com"
    */
    public static final String CHAT_SERVER_URL = null;
//    public static final String CHAT_SERVER_URL = "http://pedabook.webdesigninguk.co/api/";//http://pedabook.webdesigninguk.co/api/get_session

    public static final String SESSION_INFO_ENDPOINT = CHAT_SERVER_URL + "get_session";


    // *** The code below is to validate this configuration file. You do not need to modify it  ***

    public static String webServerConfigErrorMessage;
    public static String hardCodedConfigErrorMessage;

    public static boolean areHardCodedConfigsValid() {
        if (OpenTokConfig.API_KEY != null && !OpenTokConfig.API_KEY.isEmpty()
                && OpenTokConfig.SESSION_ID != null && !OpenTokConfig.SESSION_ID.isEmpty()
                && OpenTokConfig.TOKEN != null && !OpenTokConfig.TOKEN.isEmpty()) {
            return true;
        }
        else {
            hardCodedConfigErrorMessage = "API KEY, SESSION ID and TOKEN in OpenTokConfig.java cannot be null or empty.";
            return false;
        }
    }

    public static boolean isWebServerConfigUrlValid(){
        if (OpenTokConfig.CHAT_SERVER_URL == null || OpenTokConfig.CHAT_SERVER_URL.isEmpty()) {
            webServerConfigErrorMessage = "CHAT_SERVER_URL in OpenTokConfig.java must not be null or empty";
            return false;
        } else if ( !( URLUtil.isHttpsUrl(OpenTokConfig.CHAT_SERVER_URL) || URLUtil.isHttpUrl(OpenTokConfig.CHAT_SERVER_URL)) ) {
            webServerConfigErrorMessage = "CHAT_SERVER_URL in OpenTokConfig.java must be specified as either http or https";
            return false;
        } else if ( !URLUtil.isValidUrl(OpenTokConfig.CHAT_SERVER_URL) ) {
            webServerConfigErrorMessage = "CHAT_SERVER_URL in OpenTokConfig.java is not a valid URL";
            return false;
        } else {
            return true;
        }
    }
}
