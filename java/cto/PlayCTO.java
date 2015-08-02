package cto;

import java.text.NumberFormat;
import java.util.Random;

/**
 * @author BernierD
 */
public class PlayCTO
{
	private String name;

	private ITResource itRes;

	private Random random = new Random();
	private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();

	public static void main(String[] args) {
		new PlayCTO().play();
	}

	private void play() {
		try
		{

			itRes = new ITResource();

			System.out.println("\nWelcome to 'Play the CTO'.\n\nYour goal is to earn One Million dollars, by creating an IT resource that generates revenue.");
			System.out.println("New functionality improves the revenue that your IT resource can generate,\nand Infrastructure improvements improve the reliability of your software, and make it\ncheaper to add new functionality.\n");
			name = Prompt.ask("What's your name? ");
			System.out.println("Hello, " + name + ".  You'll start with " +
								currencyFormat.format(itRes.getRevenue()) +
								" to build your IT Resource.\n\n");


			boolean crashed = false;
			while(0 < itRes.getRevenue() && itRes.getRevenue() < 1000000)
			{
				try {
					takeTurn();
				}
				catch (CrashException cx) {
					itRes.resetTurnsSinceLastCrash();
				}
				itRes.incrementTurnsSinceLastCrash();
			}

			if (itRes.getRevenue() < 1) {
				System.out.println("You lost all your revenue!  Your name will be recorded in history as a FAILURE!");
			}
			else {
				System.out.println("Congratulations!  You built an IT Empire up from the dust!  You're a hero!  InformationWeek is here for an interview!");
			}
			System.out.println("\nThanks for playing!");
		}
		catch (Exception x) {
			System.out.println("Exception occurred:\n" + x);
		}
	}



	private void takeTurn() throws Exception
	{
		int featuresCost = itRes.getNewFeaturesCost();
		int infrastructureCost = itRes.getInfrastructureImprovementCost();

		System.out.println("~*~*~*~*~*~*~*~*~\n");
		System.out.println("Cost to add new features:             " + currencyFormat.format(featuresCost));
		System.out.println("Cost to improve infrastructure:       " + currencyFormat.format(infrastructureCost));
		System.out.println("Estimated chance of a crash:          " + itRes.getChanceOfCrash() + "%");
		if (itRes.getFIR() > .7)
			System.out.print("\nYour Lead Architect highly recommends you improve your architecture to reduce your risk of a crash!");
		System.out.println();

		System.out.println("Current revenue:                      " + currencyFormat.format(itRes.getRevenue()) + "\n");

		String choice = Prompt.ask("Do you want to improve (F)eatures or (I)nfrastructure? ");
		boolean improveFeatures = choice.trim().toLowerCase().equals("f");

		if (improveFeatures) {
			itRes.addNewFeatures();
			System.out.println("Adding new functionality, at a cost of " +
								currencyFormat.format(featuresCost) +
								".  You're left with " +
								currencyFormat.format(itRes.getRevenue()) + ".");
		}
		else {
			itRes.updateInfrastructure();
			System.out.println("Improving your system's stability, at a cost of " +
								currencyFormat.format(infrastructureCost) +
								".  You're left with " +
								currencyFormat.format(itRes.getRevenue()) + ".");
		}
		Prompt.pause();

		// roll for crash -- chance for crash = FIR
		double crashRoll = random.nextDouble();
		if (itRes.willCrashAt(crashRoll))
		{
			int crashCost = itRes.getCrashCost();
			itRes.addRevenue(-1 * crashCost);

			System.out.println("Oh no!  Your system just crashed, costing you " +
								currencyFormat.format(crashCost) + ", at " +
								currencyFormat.format(crashCost / (double)itRes.getUsers()) +
								" per user!  You're left with " +
								currencyFormat.format(itRes.getRevenue()) + "!");
			System.out.println("Revenue won't be collected while we try to stabilize the system.");

			Prompt.pause();
			throw new CrashException();
		}


		int newUsers = itRes.getNewUsers(improveFeatures);  // more users if you improve features
		itRes.addUsers(newUsers);

		System.out.print("Because of your ");
		System.out.print(improveFeatures ? "great new functionality" : "commitment to stability");
		System.out.println(", you gained " + newUsers + " new users!");

		System.out.println("You now have a total of " + itRes.getUsers() + " users.");
		System.out.println();

		int newRevenue = itRes.getNewRevenue(newUsers);
		itRes.addRevenue(newRevenue);
		System.out.println("Earnings this turn:  " + currencyFormat.format(newRevenue));
		System.out.println("Total Revenue:       " + currencyFormat.format(itRes.getRevenue()));

		Prompt.pause();
	}

	private class CrashException extends Exception {
	}

}
