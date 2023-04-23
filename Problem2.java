import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Word List Brace Expansion
approach: we have to make lists of character blocks and form permutations on blocks
time: average size of block = k ; then total blocks b = len(s)/k; O(k^b)
space: O(len(s)) since in backtracking we use only string builder
 */
public class Problem2 {
    String[] res;
    List<String> result;
    private String[] expand(String s) {
        result = new ArrayList<>();
        List<List<Character>> blocks = new ArrayList<>();

        for (int i=0;i<s.length();i++) {
            char c = s.charAt(i);
            List<Character> block = new ArrayList<>();
            if (c=='{') {
                i++;
                while (s.charAt(i)!='}') {
                    if (s.charAt(i)!=',') {
                        block.add(s.charAt(i));
                    }
                    i++;
                }
            } else {
                block.add(c);
            }
            blocks.add(block);
        }
        StringBuilder path = new StringBuilder();
        backtrack(blocks, 0, path);
        res = new String[result.size()];
        int j=0;
        for (String permutation:result) {
            res[j++] = permutation;
        }
        Arrays.sort(res);
        return res;
    }

    private void backtrack(List<List<Character>> blocks, int index, StringBuilder path) {
        //base
        if (index==blocks.size()) {
            result.add(path.toString());
            return;
        }

        List<Character> block = blocks.get(index);
        //logic
        for (int i=0;i<block.size();i++) {
            char c = block.get(i);
            path.append(c);
            backtrack(blocks, index+1, path);
            path.setLength(path.length()-1);
        }
    }

    public static void main(String []args) {
        Problem2 problem2 = new Problem2();
        problem2.expand("{b,a}c{e,d}");
    }
}
