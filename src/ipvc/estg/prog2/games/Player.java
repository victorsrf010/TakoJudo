package ipvc.estg.prog2.games;

public abstract class Player<ActionType, StateType extends State<ActionType, ResultType, StateType>, ResultType> {

    /**
     * nome do jogador
     */
    private String name;

    /**
     * nome do jogador
     * @param name
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * @return nome do jogador
     */
    public String getName() {
        return name;
    }

    /**
     * Método abstrato que deve imprimir para a consola as estatísticas deste jogador
     */
    public abstract void printStats();

    /**
     * Método que retorna uma ação num jogo com base em determinado estado
     * @param state estado atual do jogo
     * @return ação selecionada pelo jogador
     */
    public abstract ActionType getAction(StateType state);

    /**
     * Método que notifica o jogador que iniciou um novo jogo
     * @param position posição na mesa (de 0 até número de jogadores)
     */
    public abstract void eventNewGame(int position);

    /**
     * Método que notifica o jogador que algum jogador fez determinada ação.
     * O jogador na posição @playerPos jogou com a ação @action e obteve o novo estado de jogo @newState
     * @param playerPos posição na mesa (de 0 até número de jogadores)
     * @param action ação tomada
     * @param newState novo estado de jogo
     */
    public abstract void eventAction(int playerPos, ActionType action, StateType newState);

    /**
     * Método que notifica o jogador do resultado de um jogo
     * @param playerPos posição do jogador na mesa que obteve o resultado @result
     * @param result resultado do jogo
     */
    public abstract void eventResult(int playerPos, ResultType result);

    /**
     * Método que notifica o jogador que o jogo acabou.
     * @param finalState último estado visível do jogo
     */
    public abstract void eventEndGame(StateType finalState);
}
