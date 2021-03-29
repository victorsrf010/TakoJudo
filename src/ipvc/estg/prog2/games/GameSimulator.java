package ipvc.estg.prog2.games;

import java.util.ArrayList;

/**
 * Permite simular uma série de jogos entre determinados jogadores
 * @param <PlayerType> classe do tipo de jogador
 */
public abstract class GameSimulator<
        ActionType,
        ResultType,
        StateType extends State<ActionType, ResultType, StateType>,
        PlayerType extends Player<ActionType, StateType, ResultType>> {

    /**
     * Armazena as permutações possíveis de posições dos jogadores na mesa
     */
    private ArrayList<PlayerType[]> permutations;

    /**
     * Posições atuais nas mesa
     */
    private int currentPermutation = 0;

    /**
     * Adaptado de https://www.geeksforgeeks.org/heaps-algorithm-for-generating-permutations/
     * Permite gerar todas as permutações possíveis de lugares numa mesa de jogo
     * @param a
     * @param size
     * @param n
     */
    private void heapPermutation(PlayerType a[], int size, int n)
    {
        // if size becomes 1 then prints the obtained
        // permutation
        if (size == 1) {
            this.permutations.add(a.clone());
        }

        for (int i = 0; i < size; i++) {
            heapPermutation(a, size - 1, n);

            // if size is odd, swap 0th i.e (first) and
            // (size-1)th i.e (last) element
            if (size % 2 == 1) {
                PlayerType temp = a[0];
                a[0] = a[size - 1];
                a[size - 1] = temp;
            }

            // If size is even, swap ith
            // and (size-1)th i.e last element
            else {
                PlayerType temp = a[i];
                a[i] = a[size - 1];
                a[size - 1] = temp;
            }
        }
    }

    public GameSimulator(PlayerType... players) {
        this.permutations = new ArrayList<>();
        this.heapPermutation(players, players.length, players.length);
        this.currentPermutation = 0;
    }

    /**
     * Muda a ordem dos jogadores. A ordem é mudada de forma a garantir que todas as combinações de lugares são
     * satisfeitas.
     * Exemplo: 2 jogadores [a, b]
     * iteração 1: a,b
     * iteração 2: b,a
     * iteração 3: a,b (volta à posição inicial pois já deu a volta)
     * Exemplo: 3 jogadores [x, y, z]
     * iteração 1: x,y,z
     * iteração 2: y,x,z
     * iteração 3: z,x,y
     * iteração 4: x,z,y
     * iteração 5: y,z,x
     * iteração 6: z,y,x
     * iteração 6: x,y,z (volta à posição inicial pois já deu a volta)
     */
    public void changePlayerPositions() {
        this.currentPermutation++;
        if(this.currentPermutation >= this.permutations.size()) {
            this.currentPermutation = 0;
        }
    }

    /**
     * Inicia um novo jogo com um estado inicial
     */
    protected abstract StateType initGame();

    /**
     * Correr a simulação
     */
    public void runSimulation() {

        StateType state = initGame();
        PlayerType[] players = this.getPlayerPositions();

        // notifica os jogadores que o jogo começou
        for(int pos = 0; pos < players.length; ++pos) {
            players[pos].setCurrentPos(pos);
            players[pos].eventNewGame();
        }

        //play a turn
        while (!state.isFinished()){
            boolean validAction;
            ActionType action;
            int pos = state.getActingPlayer();

            do {
                action = players[pos].getAction((StateType) state.getClone());

                //validate play
                validAction = state.validateAction(action);

            }while (!validAction);

            state.play(action);

            // notifica os jogadores de que a ação ocorreu
            for(PlayerType player : players) {
                player.eventAction(pos, action, (StateType) state.getClone());
            }
        }

        // informa o estado de um evento antes dos resultados
        this.beforeEndGame(state);

        // notifica os jogadores do resultado que cada jogador obteve
        // notifica igualmente o fim do jogo
        for(PlayerType player : players) {
            for(int pos = 0; pos < players.length; ++pos) {
                player.eventResult(pos, state.getResult(pos));
            }
            player.eventEndGame(state.getClone());
        }

        // informa o estado de um evento antes dos resultados
        this.endGame(state);
    }

    /**
     * Executado quando o jogo é terminado
     * @param state estado final
     */
    protected void endGame(StateType state) {}

    /**
     * Executado antes de um jogo terminar
     * @param state estado final
     */
    protected void beforeEndGame(StateType state) {}

    /**
     * Imprime estatísticas para todos os jogadores
     */
    public void printStats() {
        for(PlayerType p : this.permutations.get(0)) {
            p.printStats();
        }
    }

    /**
     * @return obtem todos os jogadores
     */
    public PlayerType[] getPlayers() {
        return this.permutations.get(0);
    }

    /**
     * @return obtem os jogadores para a permutação atual
     */
    public PlayerType[] getPlayerPositions() {
        return this.permutations.get(this.currentPermutation);
    }

    /**
     *
     */
}
