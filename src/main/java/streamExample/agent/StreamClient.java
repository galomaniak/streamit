package streamExample.agent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import streamExample.agent.ui.SingleVideoDisplayWindow;
import streamExample.handler.StreamFrameListener;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.InetSocketAddress;
import streamExample.agent.ui.StreamClientDisplayWindow;

public class StreamClient {
    protected final static Logger logger = LoggerFactory.getLogger(StreamClient.class);
	private final static Dimension startDimension = new Dimension(320,240);
//	private static SingleVideoDisplayWindow displayWindow;
        private static StreamClientDisplayWindow displayWindow;
        
	public static void main(String[] args) {
            displayWindow = new StreamClientDisplayWindow("Istreamit Client", startDimension);
//        displayWindow = new SingleVideoDisplayWindow("Istreamit Client", startDimension);
		displayWindow.setVisible(true);

        // todo: add in agent changing dimensions OR create agent after connecting and getting resolution
		StreamClientAgent clientAgent = new StreamClientAgent(new StreamFrameListenerIMPL() ,startDimension);
		clientAgent.connect(new InetSocketAddress(StreamServer.HOSTNAME, StreamServer.PORT));
	}

	protected static class StreamFrameListenerIMPL implements StreamFrameListener {
		private volatile long count = 0;
		@Override
		public void onFrameReceived(BufferedImage image) {
			displayWindow.updateImage(image);			
		}
	}
}
