package ipvc.estg.prog2.games;

public abstract class State<ActionType, ResultType, StateType extends State<ActionType, ResultType, StateType>> {

    /**
     * Obtem o número de jogadores participantes neste jogo
     * @return
     */
    public abstract int getNumPlayers();

    /**
     * @param action ação a ser realizada pelo jogador atual
     * @return verdadeiro se a ação for válida
     */
    public abstract boolean validateAction(ActionType action);

    /**
     * Atualiza o estado do jogo com base numa ação
     * @param actionType ação a ser realizada pelo jogador atual
     */
    protected abstract void update(ActionType actionType);

    /**
     * Imprime o estado do jogo no ecrã
     */
    public abstract void display();

    /**
     * @return verdadeiro se o jogo já tiver terminado
     */
    public abstract boolean isFinished();

    /**
     * @return indíce do jogador atual
     */
    public abstract int getActingPlayer();

    /**
     * Tenta realizar determinada jogada
     * @param action ação selecionada pelo jogador atual
     * @return verdadeiro se a ação for valida e bem sucedida
     */
    public boolean play(ActionType action) {
        if(!validateAction(action)) {
            return false;
        }
        update(action);
        return true;
    }

    /**
     * @return uma cópia do estado do jogo
     */
    public abstract StateType getClone();

    /**
     * @param pos do jogador na mesa
     * @return resultado do jogador na posição @pos
     */
    public abstract ResultType getResult(int pos);
}
