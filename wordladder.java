class Pair {
    String s;
    int level;
    Pair(String s,int level) {
        this.s = s;
        this.level = level;
    }
}

class Solution
{
    public int wordLadderLength(String startWord, String targetWord, String[] wordList)
    {
        List<String> list = new ArrayList<>();
        
        for(int i=0;i<wordList.length;i++) {
            list.add(wordList[i]);
        }
        
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(startWord,1));
        
        if(list.contains(startWord))
            list.remove(String.valueOf(startWord));
        
        //O(Length of wordList)
        while(!q.isEmpty()) {
            Pair p = q.peek();
            int level = p.level;
            String curString = p.s;
            q.poll();
            
            if(targetWord.equals(curString))
                return level;
            
            int length = curString.length();
            //for the index to update.....
            //O(length of word)
            for(int i=0;i<length;i++) {
                //[a,b,c......z]
                //O(26)
                for(char c='a';c<='z';c++) {
                    char ch[] = curString.toCharArray();
                    ch[i] = c;
                    String changeString = String.valueOf(ch);
                    if(list.contains(changeString)) {
                        q.offer(new Pair(changeString,level+1));
                        list.remove(String.valueOf(changeString));
                    }
                }
            }
        }
        return 0;
    }
}

// Tc -> O(size of wordList * length of word * 26)
