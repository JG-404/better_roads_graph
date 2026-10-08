public class main {
     public static void main(String[] args) {
        
        String[] cidades = {
            "Seattle",          // 0
            "Portland",         // 1
            "Boise",            // 2
            "Lake Tahoe",       // 3
            "San Francisco",    // 4
            "Los Angeles",      // 5
            "San Diego",        // 6
            "Palm Springs",     // 7
            "Las Vegas",        // 8
            "Grand Canyon",     // 9
            "Phoenix",          // 10
            "Salt Lake City",   // 11
            "Yellowstone",      // 12
            "Denver",           // 13
            "Vail",             // 14
            "Aspen",            // 15
            "Colorado Springs", // 16
            "Minneapolis",      // 17
            "Omaha",            // 18
            "Kansas City",      // 19
            "Dallas",           // 20
            "San Antonio",      // 21
            "Houston",          // 22
            "New Orleans",      // 23
            "Milwaukee",        // 24
            "Chicago",          // 25
            "St. Louis",        // 26
            "Little Rock",      // 27
            "Memphis",          // 28
            "Detroit",          // 29
            "Indianapolis",     // 30
            "Nashville",        // 31
            "Atlanta",          // 32
            "Albany",           // 33
            "Boston",           // 34
            "New York",         // 35
            "Washington",       // 36
            "Richmond",         // 37
            "Orlando",          // 38
            "Fort Lauderdale",  // 39
            "Miami"             // 40
        }; 

        try{
            Graph<String,Integer> usGraph = new Graph<String, Integer>(cidades);
        }catch(Exception e){
            System.err.println("Erro ao inicializar o grafo: " + e.getMessage());
            e.printStackTrace();
        }
        
    }
}

        



