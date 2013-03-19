package entities;

import java.util.Iterator;

import lang.EmptyListException;
import util.SinglyLinkedList;
import dispatcher.Clock;
import dispatcher.EventManager;

public class ProcessList extends SinglyLinkedList<LogicalProcess> {
	private EventManager manager;

	public ProcessList(EventManager manager) {
		this.manager = manager;
	}

	public void processAll() {
		Iterator<LogicalProcess> iter = this.iterator();
		LogicalProcess process;
		while (iter.hasNext()) {
			process = iter.next();
			if (process.isIdle()) {
				try {
					process.process();
					manager.scheduleDeparture(
							Clock.getTime() + process.getProcessTime(), process);
				} catch (EmptyListException ex) {
				}
			}
		}
	}
}
