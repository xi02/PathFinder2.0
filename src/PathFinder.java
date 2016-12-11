import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
//this is a test
public class PathFinder {
    public static void main(String[] args)
            throws FileNotFoundException, IOException {
        String filename = "src/input_3.txt";
        if (args.length > 0) {
        	filename = args[0];
        }
        
        List<String> answer = parseFile(filename);
        System.out.println(answer);
    }
    
    static List<String> parseFile(String filename)
    		throws FileNotFoundException, IOException {
    	/*
    	 *  Don't modify this function
    	 */
        BufferedReader input = new BufferedReader(new FileReader(filename));
        List<String> allLines = new ArrayList<String>();
        String line;
        while ((line = input.readLine()) != null) {
        	allLines.add(line);
        }
        input.close();

        return parseLines(allLines);    	
    }
    
    static List<String> parseLines(List<String> lines){
        Set<String> visitedNode = new HashSet<>();
        Map<String, List<String>> graph = new HashMap<>();
        List<String> res = new ArrayList<>();
        String[] goal = lines.get(0).split(" ");
        String start = goal[0];
        String end = goal[1];
        for (int i = 1; i < lines.size(); i++){
            String line = lines.get(i);
            String[] node = line.trim().split(":");
            List<String> list = Arrays.asList(node[1].trim().split(""));
            graph.put(node[0].trim(), list);
        }
        traversal(start, end, "", visitedNode, res, graph);
        return res;
    }
    
    private static void traversal(String start, String end, String path, Set<String> visitedNode, List<String> res, Map<String, List<String>> graph){
        String curPath = path;
        curPath += start;
        visitedNode.add(start);
        if(start.equals(end)){
            res.add(curPath);
        }else {
            List<String> connectedNodes = graph.get(start);
            if (connectedNodes != null){
                for (String node: connectedNodes){
                    if(!visitedNode.contains(node))
                        traversal(node, end, curPath, visitedNode, res, graph);
                }
            }
        }
        visitedNode.remove(start);
    }
}