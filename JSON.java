import java.util.HashMap;
import java.util.Iterator;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.json.JSONArray;
import org.json.JSONObject;

class JSON {
    public JSON () {}

    /**
     * @param str String value in JSON format.
     * @return HashMap that is converted from JSON value.
     */
    public static HashMap<String, String> convertJSONtoHashMap(String str) {
        // Sample data
        // str = "[{\"code\":\"1000\",\"message\":\"success\",\"data\":{\"domainRegNo\":\"D6A000165\",\"fqdn\":\"webnic.my\",\"domainType\":\"organisation\",\"domainRegDate\":\"2008-01-09\",\"domainExpDate\":\"2023-01-09\",\"lastUpdated\":\"2021-10-11 12:19:36\",\"resellerRef\":\"R039\",\"resellerOrgName\":\"Qinetics Solutions Sdn Bhd\",\"registrantOrgName\":\"Molek Abadi Sdn Bhd\",\"registrantOrgRegNo\":\"582030 U\",\"registrantAddress1\":\"20-5, Level 20, Q Sentral, 2A,\",\"registrantAddress2\":\"Jalan Stesen Sentral 2, KL Sentral\",\"registrantPostcode\":\"50470\",\"registrantTown\":\"KL\",\"registrantState\":\"Wilayah Persekutuan\",\"registrantCountry\":\"Malaysia\",\"registrantOfficeNo\":\"601-62061931\",\"registrantFax\":\"-\",\"registrantEmail\":\"support@molekabadi.com\",\"registrantFirstName\":,\"registrantLastName\":,\"registrantIc\":,\"adminOrgName\":\"Molek Abadi Sdn Bhd\",\"adminAddress1\":\"20-5, Level 20, Q Sentral, 2A,\",\"adminAddress2\":\"Jalan Stesen Sentral 2, KL Sentral\",\"adminPostcode\":\"50470\",\"adminTown\":\"KL\",\"adminState\":\"Wilayah Persekutuan\",\"adminCountry\":\"Malaysia\",\"adminOfficeNo\":\"601-62061931\",\"adminFax\":\"603-21422611\",\"adminEmail\":\"support@molekabadi.com\",\"adminFirstName\":\"Registration\",\"adminLastName\":\"Team\",\"technicalOrgName\":\"Web Commerce Communications Limited.\",\"technicalAddress1\":\"Lot 2-2, Incubator 1\",\"technicalAddress2\":\"Technology Park Malaysia -\",\"technicalPostcode\":\"57000\",\"technicalTown\":\"Kuala Lumpur\",\"technicalState\":\"Wilayah Persekutuan\",\"technicalCountry\":\"Malaysia\",\"technicalOfficeNo\":\"603-89966788\",\"technicalFax\":\"603-89968788\",\"technicalEmail\":\"support@webnic.cc\",\"technicalFirstName\":\"WebNIC\",\"technicalLastName\":\"Support\",\"billingOrgName\":\"Web Commerce Communications Limited.\",\"billingAddress1\":\"Lot 2-2, Incubator 1\",\"billingAddress2\":\"Technology Park Malaysia -\",\"billingPostcode\":\"57000\",\"billingTown\":\"Kuala Lumpur\",\"billingState\":\"Wilayah Persekutuan\",\"billingCountry\":\"Malaysia\",\"billingOfficeNo\":\"603-89966788\",\"billingFax\":\"603-89968788\",\"billingEmail\":\"support@webnic.cc\",\"billingFirstName\":\"WebNIC\",\"billingLastName\":\"Support\",\"hostname1\":\"donald.ns.cloudflare.com\",\"ipv4address1\":,\"ipv6address1\":,\"hostname2\":\"fiona.ns.cloudflare.com\",\"ipv4address2\":,\"ipv6address2\":}}]";

        HashMap<String, String> resultMap = new HashMap<String, String>();

        try {
            // Convert String into a JsonElement Object.
            JsonElement jelement = new Gson().fromJson(str, JsonElement.class);

            // Check if the JsonElement is a JsonObject or a JsonArray.
            if (jelement instanceof JsonObject) {
                // Convert JsonElement into JsonObject
                JsonObject jsonObject = jelement.getAsJsonObject();
                JSONObject object = new JSONObject(jsonObject.toString());

                // Direct convert Json to HashMap
                resultMap = new Gson().fromJson(str, HashMap.class);
            } else if (jelement instanceof JsonArray) {
                // Convert JsonElement into JsonArray
                JsonArray jarray = jelement.getAsJsonArray();
                JSONArray array = new JSONArray(jarray.toString());

                // Loop through the JsonArray to get the keys 
                for (int i = 0; i < array.length(); i++) {
                    Iterator<?> keys = array.getJSONObject(i).keys();

                    // Loop through the values
                    while (keys.hasNext()) {
                        String key = (String) keys.next();
                        String value = array.getJSONObject(i).getString(key);
                        resultMap.put(key, value);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return resultMap;
    }
}