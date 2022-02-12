class testObject {
		private String data;
		private int priority;
		private int height;
		
		testObject(){
			data = null;
			priority = height = 0;
		}
		
		testObject(String d, int p, int h){
			data = d;
			priority = p;
			height = h;
		}
		
		public String getData() {return data;}
		public int getPriority() {return priority;}
		public int getHeight() {return height;}
}