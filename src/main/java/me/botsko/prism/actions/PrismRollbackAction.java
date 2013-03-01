package me.botsko.prism.actions;


public class PrismRollbackAction extends GenericAction {
	
	public class PrismRollbackActionData {
		public String onBehalfOf;
		public int originalBlock_id;
		public int originalBlock_subid;
		public int newBlock_id;
		public int newBlock_subid;
		public int parent_id;
	}
	
	/**
	 * 
	 */
	protected PrismRollbackActionData actionData;
	
	
	/**
	 * 
	 * @param action_type
	 * @param block
	 * @param player
	 */
	public PrismRollbackAction( String action_type, int originalBlock_id, int originalBlock_subid, int newBlock_id, int newBlock_subid, String playername, int parent_id ){
		
		super(action_type, playername);
		
		actionData = new PrismRollbackActionData();
		
		if(playername != null){
			actionData.originalBlock_id = originalBlock_id;
			actionData.originalBlock_subid = originalBlock_subid;
			actionData.newBlock_id = newBlock_id;
			actionData.newBlock_subid = newBlock_subid;
			actionData.parent_id = parent_id;
		}
		
		// Set data from current block
		setDataFromObject();
		setObjectFromData();
		
	}
	
	
	/**
	 * 
	 */
	public void setData( String data ){
		this.data = data;
		setObjectFromData();
	}
	
	
	/**
	 * 
	 */
	protected void setDataFromObject(){
		data = gson.toJson(actionData);
	}
	
	
	/**
	 * 
	 */
	protected void setObjectFromData(){
		if(data != null){
			actionData = gson.fromJson(data, PrismRollbackActionData.class);
		}
	}

	
	/**
	 * @return the onBehalfOf
	 */
	public String getOnBehalfOf() {
		return actionData.onBehalfOf;
	}

	
	/**
	 * @return the originalBlock_id
	 */
	public int getOriginalBlockId() {
		return actionData.originalBlock_id;
	}

	
	/**
	 * @return the originalBlock_subid
	 */
	public int getOriginalBlockSubId() {
		return actionData.originalBlock_subid;
	}

	
	/**
	 * @return the newBlock_id
	 */
	public int getNewBlockId() {
		return actionData.newBlock_id;
	}

	
	/**
	 * @return the newBlock_subid
	 */
	public int getNewBlockSubId() {
		return actionData.newBlock_subid;
	}

	
	/**
	 * @return the parent_id
	 */
	public int getParentId() {
		return actionData.parent_id;
	}
}