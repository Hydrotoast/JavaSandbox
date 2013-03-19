package edu.giocc.util.router.ContentBasedRouter;

import edu.giocc.util.router.Message;
import edu.giocc.util.router.QueueChannel;

public class ContentChannel implements QueueChannel<Message>, Comparable<QueueChannel<? extends Message>> {

	@Override
	public int compareTo(QueueChannel<? extends Message> o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCongestion() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void dispatch(Message content) {
		// TODO Auto-generated method stub
		
	}

}
