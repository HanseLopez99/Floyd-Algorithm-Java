import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

class FloydTest{

	@Test
    public void graphCenterTest() throws IOException  {
        Floyd flo = new Floyd();
        GraphModel mod = new GraphModel();
        ArrayList<GraphModel> city = flo.readTXTFile();
        ArrayList<String> modelos = mod.generateGraphArray(city);
        long[][] matrix = flo.createMatrix(modelos, city);
      
        //Ver cual es el centro
        int res = modelos.indexOf("Mixco");
        int result = flo.graphCenter(matrix);
        assertEquals(res, result);
    }

	@Test
    public void testFloyd() throws IOException {
        Floyd flo = new Floyd();
        GraphModel mod = new GraphModel();
        ArrayList<GraphModel> modelArr = flo.readTXTFile();
        ArrayList<String> graph = mod.generateGraphArray(modelArr);
        long[][] matrix = flo.createMatrix(graph, modelArr);
        String origin = "Antigua";
        String destination = "Escuintla";
        
        String res = "\nDe (Antigua) ---> (Escuintla) deberia irse por: (Antigua, Escuintla)\n";
        String result = flo.FloydAlgorithm(matrix, graph, origin, destination);
        assertEquals(result, result);
    }


}
