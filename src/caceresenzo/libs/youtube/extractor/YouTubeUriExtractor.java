package caceresenzo.libs.youtube.extractor;

import caceresenzo.libs.array.SparseArray;
import caceresenzo.libs.youtube.video.VideoMeta;
import caceresenzo.libs.youtube.video.YoutubeVideo;

@Deprecated
public abstract class YouTubeUriExtractor extends YouTubeExtractor {
	
	public YouTubeUriExtractor(String cacheDir) {
		super(cacheDir);
	}
	
	@Override
	protected void onExtractionComplete(SparseArray<YoutubeVideo> videos, VideoMeta videoMeta) {
		onUrisAvailable(videoMeta.getVideoId(), videoMeta.getTitle(), videos);
	}
	
	public abstract void onUrisAvailable(String videoId, String videoTitle, SparseArray<YoutubeVideo> videos);
	
}