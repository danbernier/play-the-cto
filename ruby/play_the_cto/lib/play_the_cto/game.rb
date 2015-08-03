module PlayTheCto
  class Game

    def initialize
      @currency_format = Object.new.tap do |o|
        def o.format(revenue)
          amount = (revenue * 100).round / 100.0
          "$#{amount}"   # We can add commas later. :p
        end
      end
    end

    def play
      @it_res = ITResource.new

      puts "\nWelcome to 'Play the CTO'.\n\nYour goal is to earn One Million dollars, by creating an IT resource that generates revenue."
      puts "New functionality improves the revenue that your IT resource can generate,\nand Infrastructure improvements improve the reliability of your software, and make it\ncheaper to add new functionality.\n"

      print "What's your name? "
      name = gets.chomp
      puts "Hello, #{name}.  You'll start with #{@currency_format.format(@it_res.revenue)} to build your IT Resource.\n\n"


      while 0 < @it_res.revenue && @it_res.revenue < 1000000 do
        begin
          take_turn
        rescue CrashException
          @it_res.reset_turns_since_last_crash
        end
        @it_res.increment_turns_since_last_crash
      end

      if @it_res.revenue < 1
        puts "You lost all your revenue!  Your name will be recorded in history as a FAILURE!"
      else
        puts "Congratulations!  You built an IT Empire up from the dust!  You're a hero!  InformationWeek is here for an interview!"
      end

      puts "\nThanks for playing!"
    end


    private


    def take_turn
      features_cost = @it_res.get_new_features_cost
      infrastructure_cost = @it_res.get_infrastructure_improvement_cost

      puts "~*~*~*~*~*~*~*~*~\n"
      puts "Cost to add new features:             #{@currency_format.format(features_cost)}"
      puts "Cost to improve infrastructure:       #{@currency_format.format(infrastructure_cost)}"
      puts "Estimated chance of a crash:          #{@it_res.get_chance_of_crash}%"

      if @it_res.get_fir > 0.7
        puts "\nYour Lead Architect highly recommends you improve your architecture to reduce your risk of a crash!"
      end
      puts

      puts "Current revenue:                      #{@currency_format.format(@it_res.revenue)}\n"

      print "Do you want to improve (F)eatures or (I)nfrastructure? "
      choice = gets.chomp
      improve_features = choice.strip.downcase == 'f'

      if improve_features
        @it_res.add_new_features
        puts "Adding new functionality, at a cost of #{@currency_format.format(features_cost)}.  You're left with #{@currency_format.format(@it_res.revenue)}."
      else
        @it_res.update_infrastructure
        puts "Improving your system's stability, at a cost of #{@currency_format.format(infrastructure_cost)}.  You're left with #{@currency_format.format(@it_res.revenue)}."
      end
      gets

      # roll for crash -- chance for crash = FIR
      crash_roll = rand
      if @it_res.will_crash_at(crash_roll)
        crash_cost = @it_res.get_crash_cost
        @it_res.add_revenue(-1 * crash_cost)

        puts "Oh no!  Your system just crashed, costing you #{@currency_format.format(crash_cost)}, at #{@currency_format.format(crash_cost.to_f / @it_res.num_users)} per user!  You're left with #{@currency_format.format(@it_res.revenue)}!"
        puts "Revenue won't be collected while we try to stabilize the system."

        gets
        raise CrashException.new
      end

      new_users = @it_res.get_new_users(improve_features)  # more users if you improve features
      @it_res.add_users(new_users)

      puts "Because of your #{improve_features ? "great new functionality" : "commitment to stability"}, you gained #{new_users} new users!"

      puts "You now have a total of #{@it_res.num_users} users."
      puts

      new_revenue = @it_res.get_new_revenue(new_users)
      @it_res.add_revenue(new_revenue)
      puts "Earnings this turn:  " + @currency_format.format(new_revenue)
      puts "Total Revenue:       " + @currency_format.format(@it_res.revenue)

      gets
    end
  end

	class CrashException < Exception
  end
end
