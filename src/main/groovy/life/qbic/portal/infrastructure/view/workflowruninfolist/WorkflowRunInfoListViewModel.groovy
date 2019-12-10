package life.qbic.portal.infrastructure.view.workflowruninfolist

import groovy.util.logging.Log4j2

@Log4j2
class WorkflowRunInfoListViewModel {
    List<WorkflowRunInfoListViewModelObserver> observers

    WorkflowRunInfoListViewModel(Collection<WorkflowRunInfoListViewModelObserver> observers) {
        this.observers.addAll(observers)
    }

    def registerObserver(WorkflowRunInfoListViewModelObserver observer) {
        if (observer in observers) {
            log.warn("Observer {} already registered.", observer.hashCode())
        } else {
            this.observers.add(observer)
        }
    }

    def unregisterObserver(WorkflowRunInfoListViewModelObserver observer) {
        if (observer in observers) {
            observers.remove(observer)
        } else {
            log.warn("Skipping unregister {}. {} was not found.", observer.getClass().getSimpleName(), observer.hashCode())
        }
    }
}
