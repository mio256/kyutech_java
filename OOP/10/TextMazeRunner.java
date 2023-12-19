
/**
 * コマンドライン引数に指定したテキストファイルから迷路を読み込み、脱出経路を表示する{@code main} をもつ．
 */
class TextMazeRunner {
    public static void main(String [] args) throws Exception {
        if(args.length < 1) {
            System.out.println("java TextMazeRunner mazefile [-b | -d]");
            System.out.println("  -b : using BFS (default)");
            System.out.println("  -d : using DFS");
            return;
        }
        boolean bfs = true;
        if(args.length >= 2) {
            if(args[1].equals("-d")) bfs = false;
        }
        TextMaze tm = new TextMaze(args[0]);
        if(bfs) {
            BFSMazeSolver.solve(tm);
        } else {
            DFSMazeSolver.solve(tm);
        }
    }
}
