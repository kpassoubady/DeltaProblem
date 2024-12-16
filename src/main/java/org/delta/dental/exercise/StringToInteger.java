package org.delta.dental.exercise;


/*
System.out.println(stringToInt("-456"));  // Output: -456
System.out.println(stringToInt("0")); // Output: 0
 */
public class StringToInteger {

    private static int stringToInt(String letters) {
        if (letters.isEmpty()) {
            return 0;
        }
        // converting 456
        int totalVal = 0;
        int j = 0;
        for (int i =  letters.length() - 1; i >= 0; i--) {
            char value = letters.charAt(i); // the last char
            if (value == '-') {
                return -totalVal;
            }
            int currentVal = value - '0';
            totalVal += currentVal * (int) Math.pow(10,j++);
        }
        return totalVal;
    }

    public static void main(String[] args) {
        System.out.println(stringToInt("0"));
        System.out.println(stringToInt("1230"));
        System.out.println(stringToInt("-1230"));
        System.out.println(stringToInt(""));
        //System.out.println(stringToInt("asdasda123123"));
    }

    // Spring Boot
    // VusJs
    // Kafka
    // Kibana
    // ElasticSearch
    // MariaDB
    // K8s
    // Docker
    // Argo CD
    // Temporal
    // grpc
    // Keycloak

}
