package pl.igor.pricefinder.search.pricefindersearch.searching.searcher;

import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import pl.igor.pricefinder.search.pricefindersearch.searching.SearchStatus;
import pl.igor.pricefinder.search.pricefindersearch.searching.SearchTaskDelayer;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@EqualsAndHashCode(of = "taskId")
public class SiteSearchTaskImpl implements SearchTask {

    private boolean isFinished = false;
    private boolean forcedToBeFinished = false;
    private SearchStep currentStep;
    private final SearchTaskDelayer searchTaskDelayer;
    private final UUID taskId = UUID.randomUUID();
    private Instant startedAt;
    private Instant endedAt;
    private final String taskName;

    public SiteSearchTaskImpl(SearchStep currentStep, SearchTaskDelayer searchTaskDelayer, String taskName) {
        this.currentStep = currentStep;
        this.searchTaskDelayer = searchTaskDelayer;
        this.taskName = taskName;
    }

    @Override
    public void stopExecution() {
        isFinished = true;
        //onForceStop
    }

    @Override
    public boolean isFinished() {
        return isFinished;
    }

    @Override
    public SearchStatus call() throws Exception {
        log.info("Task {} started. Id = {}", taskName, taskId);
        startedAt = Instant.now();
        while (true) {

            if (forcedToBeFinished) {
                break;
            }
            try {
                currentStep.executeStep();
                if (isLastStep()) {
                    break;
                }
                currentStep = currentStep.getNextStep();
            } catch (SearchException e) {
                e.printStackTrace();
                //handle
            } catch (Exception e) {
                e.printStackTrace();
                //handle
            }

            Thread.sleep(searchTaskDelayer.getNextDelay());
        }

        isFinished = true;
        endedAt = Instant.now();
        log.info("Task {} Finished", taskName);
        return null;
    }

    private boolean isLastStep() {
        return !(currentStep.hasNextStep());
    }
}
