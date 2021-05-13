package stringEquivalenceRelations.gitHub;

public class Solution {

  /*
  By the problem design on binarysearch.com, we have to work
  around the given method 'public String solve(String a, String b, String target)'
  so that the code can be run on the website. Even though the name 'solve'
  does not make a lot of sense, it is left as it is, so that the code can
  be run directly on the website, without any modifications.
  */
  public String solve(String a, String b, String target) {

    UnionFind unionFind = new UnionFind();
    find_disjointUnionSets(a, b, unionFind);

    return find_smallestLexicographicallyEquivalentString(target, unionFind);
  }

  public String find_smallestLexicographicallyEquivalentString(String target, UnionFind unionFind) {

    StringBuilder result = new StringBuilder();

    for (int i = 0; i < target.length(); i++) {
      char parent = (char) ('a' + unionFind.findParent(target.charAt(i) - 'a'));
      result.append(parent);
    }
    return result.toString();
  }

  public void find_disjointUnionSets(String a, String b, UnionFind unionFind) {

    for (int i = 0; i < a.length(); i++) {

      int ch_01 = unionFind.findParent(a.charAt(i) - 'a');
      int ch_02 = unionFind.findParent(b.charAt(i) - 'a');

      if (ch_01 != ch_02) {
        unionFind.union(ch_01, ch_02);
      }
    }
  }
}

class UnionFind {

  final int CHARS_IN_ALPHABET = 26;
  int[] parent;

  public UnionFind() {
    this.parent = new int[CHARS_IN_ALPHABET];
    for (int i = 0; i < CHARS_IN_ALPHABET; i++) {
      parent[i] = i;
    }
  }

  public int findParent(int ch) {
    if (parent[ch] != ch) {
      parent[ch] = findParent(parent[ch]);
    }
    return parent[ch];
  }

  // The smaller lexicographical character is made a parent of the larger lexicographical character.
  public void union(int ch_01, int ch_02) {
    if (ch_01 < ch_02) {
      parent[ch_02] = ch_01;
    } else if (ch_01 > ch_02) {
      parent[ch_01] = ch_02;
    }
  }
}
