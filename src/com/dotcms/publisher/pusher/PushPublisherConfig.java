package com.dotcms.publisher.pusher;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.dotcms.publisher.endpoint.bean.PublishingEndPoint;
import com.dotcms.publishing.PublisherConfig;

public class PushPublisherConfig extends PublisherConfig {
	public static enum Operation {
		PUBLISH,
		UNPUBLISH
	}
	public static enum AssetTypes {
		TEMPLATES,
		HTMLPAGES,
		CONTAINERS
	}
	
	private Operation operation;
	private List<PublishingEndPoint> endpoints;
	
	public PushPublisherConfig() {
		super();
	}
	
	boolean switchIndexWhenDone = false;
	
	public boolean switchIndexWhenDone(){
		return switchIndexWhenDone;
	}
	
	
	public void setSwitchIndexWhenDone(boolean switchIndexWhenDone) {
		this.switchIndexWhenDone = switchIndexWhenDone;
	}


	private enum MyConfig {
		RUN_NOW,INDEX_NAME;
	};
	

	public boolean runNow(){
		return this.get(MyConfig.RUN_NOW.toString()) !=null && new Boolean((String) this.get(MyConfig.RUN_NOW.toString()));
		
	}
	
	public Operation getOperation() {
		return operation;
	}


	public void setOperation(Operation operation) {
		this.operation = operation;
	}


	public List<PublishingEndPoint> getEndpoints() {
		return endpoints;
	}


	public void setEndpoints(List<PublishingEndPoint> endpoints) {
		this.endpoints = endpoints;
	}


	public void setRunNow(boolean once){
		this.put(MyConfig.RUN_NOW.toString(), once);
		
	}
	
	public String getIndexName(){
		return (String) this.get(MyConfig.INDEX_NAME.toString());
		
	}
	
	public void setIndexName(String name){
		this.put(MyConfig.INDEX_NAME.toString(), name);
		
		
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public List<Class> getPublishers(){
		List<Class> clazz = new ArrayList<Class>();
		clazz.add(PushPublisher.class);
		return clazz;
	}
	

	@SuppressWarnings("unchecked")
	public Set<String> getContainers() {
		if(get(AssetTypes.CONTAINERS.name()) == null){
			Set<String> containersToBuild =   new HashSet<String>();
			put(AssetTypes.CONTAINERS.name(), containersToBuild);
		}
		
		return (Set<String>) get(AssetTypes.CONTAINERS.name());
	
	}
	@SuppressWarnings("unchecked")
	public Set<String> getTemplates() {
		if(get(AssetTypes.TEMPLATES.name()) == null){
			Set<String> templatesToBuild =   new HashSet<String>();
			put(AssetTypes.TEMPLATES.name(), templatesToBuild);
		}

		return (Set<String>) get(AssetTypes.TEMPLATES.name());
	
	}
	@SuppressWarnings("unchecked")
	public Set<String> getHTMLPages() {
		if(get(AssetTypes.HTMLPAGES.name()) == null){
			Set<String> htmlPagesToBuild =   new HashSet<String>();
			put(AssetTypes.HTMLPAGES.name(), htmlPagesToBuild);
		}

		
		
		return (Set<String>) get(AssetTypes.HTMLPAGES.name());
	}
	

	public void setHTMLPages(Set<String> htmlPages) {		
		put(AssetTypes.HTMLPAGES.name(), htmlPages);
	}
	
	public void setContainers(Set<String> containers) {
		put(AssetTypes.CONTAINERS.name(), containers);
	}
	
	public void setTemplates(Set<String> templates) {
		put(AssetTypes.TEMPLATES.name(), templates);
	}
}
