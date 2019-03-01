package tw.com.stock_crawler.controller.response;

import javax.annotation.Nonnull;

public class ResponseWrapper {

	private long timestamp;
	private boolean success;
	private String msg;
	private Object data;

	// ***** ***** ***** ***** ***** Constructors

	private ResponseWrapper(Builder builder) {
		timestamp = builder.timestamp;
		success = builder.success;
		msg = builder.msg;
		data = builder.data;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public static Builder newBuilder(@Nonnull ResponseWrapper copy) {
		Builder builder = new Builder();
		builder.timestamp = copy.getTimestamp();
		builder.success = copy.isSuccess();
		builder.msg = copy.getMsg();
		builder.data = copy.getData();
		return builder;
	}

	// ***** ***** ***** ***** ***** Getter and setter methods

	public long getTimestamp() {
		return timestamp;
	}

	public boolean isSuccess() {
		return success;
	}

	public String getMsg() {
		return msg;
	}

	public Object getData() {
		return data;
	}

	// ***** ***** ***** ***** ***** Builder

	public static final class Builder {

		private long timestamp;
		private boolean success;
		private String msg;
		private Object data;

		// ***** ***** ***** ***** ***** Constructors

		public Builder() {}

		// ***** ***** ***** ***** ***** Getter and setter methods

		@Nonnull
		public Builder setTimestamp(long timestamp) {
			this.timestamp = timestamp;
			return this;
		}

		@Nonnull
		public Builder setSuccess(boolean success) {
			this.success = success;
			return this;
		}

		@Nonnull
		public Builder setMsg(@Nonnull String msg) {
			this.msg = msg;
			return this;
		}

		@Nonnull
		public Builder setData(@Nonnull Object data) {
			this.data = data;
			return this;
		}

		@Nonnull
		public ResponseWrapper build() {
			return new ResponseWrapper(this);
		}
	}
}
