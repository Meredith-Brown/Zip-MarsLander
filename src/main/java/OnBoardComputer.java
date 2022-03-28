public class OnBoardComputer implements BurnStream {

    @Override
    public int getNextBurn(DescentEvent status) {
        int burn = ((status.Velocity * status.Velocity) / (2 * status.Altitude)) + 99;
        System.out.println(burn); /*hack!*/
        return burn;
    }
}
