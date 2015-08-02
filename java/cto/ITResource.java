package cto;

/**
 * @author Dan
 */
public class ITResource
{
	private double infrastructure = 2, features = 1;
	private int revenue = 1000;
	private int users = 0;
	private int turnsSinceLastCrash = 0;




	public boolean willCrashAt (double roll) {
		return getFIR() > roll && getUsers() > 0;
	}

	public double getFIR() {
		return features / infrastructure;
	}
	public int getChanceOfCrash() {
		return (int)Math.round(getFIR() * 100);
	}
	public int getNewFeaturesCost() {
		return (int)(500 * getFIR());
	}
	public int getInfrastructureImprovementCost() {
		return Math.max(800,  (int)(infrastructure / 3));
	}

	public int getCrashCost() {
		int exposure = (int)(getUsers() / 10000);
		if (exposure < 1)
			exposure = 1;
		return (int)(exposure * getUsers() * getFIR());
	}

	private double getUserComfortFactor () {
		return Math.max((1 + getTurnsSinceLastCrash()), 10) / 10;
	}

	public int getNewUsers (boolean addedFeatures) {
		int buzzDraw = addedFeatures ? 1000 : 100;
		return (int)(buzzDraw * features * getUserComfortFactor());
	}
	public int getNewRevenue (int newUsers) {
		return (int)(getUsers() - newUsers * .75) + (newUsers * 2);
	}

	private double getFeatures() {
		return features;
	}
	private double getInfrastructure() {
		return infrastructure;
	}
	public int getRevenue() {
		return revenue;
	}
	public int getTurnsSinceLastCrash() {
		return turnsSinceLastCrash;
	}
	public int getUsers() {
		return users;
	}



	public void addNewFeatures() {
		features++;
		revenue -= getNewFeaturesCost();
	}
	public void updateInfrastructure() {
		infrastructure++;
		revenue -= getInfrastructureImprovementCost();
	}

	public void incrementTurnsSinceLastCrash () {
		this.turnsSinceLastCrash++;
	}
	public void resetTurnsSinceLastCrash() {
		this.turnsSinceLastCrash = -1;
	}

	public void addUsers (int newUsers) {
		this.users += newUsers;
	}
	public void addRevenue (int newRevenue) {
		this.revenue += newRevenue;
	}

}
