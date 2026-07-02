package com.shortthirdman.primekit.coderbyte;

import com.shortthirdman.primekit.core.agents.Action;
import com.shortthirdman.primekit.core.agents.Plan;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

class GoalOrientedActionPlannerTest {

    private static GoalOrientedActionPlanner planner;
    private static List<Action> actions;
    private static Map<String, Object> goal;
    private static Map<String, Object> initialState;

    @BeforeAll
    static void setUp() {
        planner = new GoalOrientedActionPlanner();
        initialState = Map.of(
                "service.degraded", true,
                "deployment.recent", true,
                "service.healthy", false,
                "oncall.paged", false
        );

        goal = Map.of("service.healthy", true);

        actions = List.of(
                new Action("restartService",
                        Map.of("service.degraded", true),
                        Map.of("service.degraded", false, "service.healthy", true),
                        1.0),

                new Action("rollbackDeployment",
                        Map.of("service.degraded", true, "deployment.recent", true),
                        Map.of("service.degraded", false, "service.healthy", true,
                                "deployment.recent", false),
                        5.0),

                new Action("pageOncall",
                        Map.of("service.degraded", true),
                        Map.of("oncall.paged", true),
                        10.0)
        );
    }

    @AfterAll
    static void tearDown() {
        planner = null;
        goal = Map.of();
        initialState = Map.of();
        actions = List.of();
    }

    @Test
    void plan() {
        Plan actualPlan = planner.plan(initialState, goal, actions).orElseThrow();

        var expectedAction = new Action("restartService",
                Map.of("service.degraded", true),
                Map.of("service.degraded", false, "service.healthy", true),
                1.0);
        Plan expectedPlan = new Plan(List.of(expectedAction), 1.0);

        Assertions.assertThat(actualPlan).isNotNull();
        Assertions.assertThat(actualPlan).isEqualTo(expectedPlan);
        Assertions.assertThat(actualPlan).hasNoNullFieldsOrProperties();
    }
}