module PlayTheCto

  class ITResource
    def initialize
      @infrastructure = 2.0
      @features = 1.0
      @revenue = 1000
      @num_users = 0
      @turns_since_last_crash = 0
    end

    attr_reader :revenue, :num_users

    def will_crash_at(roll)
      get_fir > roll && num_users > 0
    end

    def get_fir
      @features.to_f / @infrastructure
    end

    def get_chance_of_crash   # view logic
      (get_fir * 100).round
    end

    def get_new_features_cost
      (500 * get_fir).round
    end

    def get_infrastructure_improvement_cost
      [800, @infrastructure / 3].max
    end

    def get_crash_cost
      exposure = [1.0, num_users / 10000.0].min
      (exposure * num_users * get_fir).to_i
    end

    def get_new_users(added_features)
      buzz_draw = added_features ? 1000 : 100
      (buzz_draw * @features * get_user_comfort_factor).to_i
    end

    def get_new_revenue(new_users)  # new_users is int
      ((@num_users - new_users * 0.75) + (new_users * 2)).to_i
    end

    def add_new_features
      @features += 1
      @revenue -= get_new_features_cost
    end

    def update_infrastructure
      @infrastructure += 1
      @revenue -= get_infrastructure_improvement_cost
    end

    def increment_turns_since_last_crash
      @turns_since_last_crash += 1
    end

    def reset_turns_since_last_crash
      @turns_since_last_crash = -1
    end

    def add_users(new_users)
      @num_users += new_users
    end

    def add_revenue(new_revenue)
      @revenue += new_revenue
    end

    private

    def get_user_comfort_factor
      [1 + @turns_since_last_crash, 10].max / 10
    end
  end
end
