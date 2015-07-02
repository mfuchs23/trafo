package org.dbdoclet.trafo.script;

import java.util.Collection;
import java.util.HashMap;

public class Namespace {

	private HashMap<String, AttributeRule> attributeRuleMap;
	private String name;
	private HashMap<String, NodeRule> nodeRuleMap;
	private HashMap<String, Section> sectionMap;

	public Namespace(String name) {

		this.setName(name);
		sectionMap = new HashMap<>();
		nodeRuleMap = new HashMap<>();
		attributeRuleMap = new HashMap<>();
	}

	public void addAttributeRule(AttributeRule attributeRule) {
		
		if (attributeRule == null) {
			return;			
		}
		
		attributeRuleMap.put(attributeRule.getName(), attributeRule);
	}
	
	public void addNodeRule(NodeRule nodeRule) {
		
		if (nodeRule == null) {
			return;			
		}
		
		nodeRuleMap.put(nodeRule.getName(), nodeRule);
	}
	
	public void addSection(Section section) {
		
		if (section == null) {
			return;			
		}
		
		sectionMap.put(section.getName(), section);
	}
	
	public AttributeRule findAttributeRule(String name) {
		return attributeRuleMap.get(name);
	}

	public NodeRule findNodeRule(String name) {
		return nodeRuleMap.get(name);
	}

	public Section findSection(String name) {
		return sectionMap.get(name);
	}

	public Collection<AttributeRule> getAttributeRules() {
		return attributeRuleMap.values();
	}

	public String getName() {
		return name;
	}

	public Collection<NodeRule> getNodeRules() {
		return nodeRuleMap.values();
	}

	public Collection<Section> getSections() {
		return sectionMap.values();
	}

	public void setName(String name) {
		this.name = name;
	}

	public Section addSection(String sectionName) {

		if (sectionName == null || sectionName.trim().length() == 0) {
			return null;
		}
		
		Section section = new Section(sectionName);
		addSection(section);
		return section;
	}

	public Section findOrCreateSection(String name) {

		Section section = findSection(name);
		
		if (section == null) {
			section = new Section(name);
			addSection(section);
		}
		
		return section;
	}
}