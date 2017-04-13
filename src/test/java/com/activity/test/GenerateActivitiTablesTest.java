package com.activity.test;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
public class GenerateActivitiTablesTest {

	@Test
	public void testGenerateActivitiTables() {
		ProcessEngineConfiguration processEngineConfiguration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
		processEngineConfiguration.setJdbcDriver("com.mysql.jdbc.Driver");
		processEngineConfiguration.setJdbcUrl("jdbc:mysql://localhost:3306/db_activiti");
		processEngineConfiguration.setJdbcUsername("root");
		processEngineConfiguration.setJdbcPassword("123456");
		/**
		 * create 25 tables.
		 */
		processEngineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
		
		ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
	}

	/**
	 * 使用xml配置 简化
	 */
	@Test
	public void testCreateTableWithXml(){
	    // 引擎配置
	    ProcessEngineConfiguration processEngineConfiguration=ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
	    // 获取流程引擎对象
	    ProcessEngine processEngine=processEngineConfiguration.buildProcessEngine();
	}
	
	private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	@Test
	public void deploy(){
		Deployment deployment = processEngine.getRepositoryService().createDeployment().addClasspathResource("diagrams/MyProcess.bpmn")
				.addClasspathResource("diagrams/MyProcess.png").name("myProcess").deploy();
		System.out.println("deploy id:"+deployment.getId());
		System.out.println("deploy name:"+deployment.getName());
	}
	@Test
	public void start(){
		ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey("myProcess");
		System.out.println("processInstance id:"+processInstance.getId());
		System.out.println("processInstance definition id:"+processInstance.getProcessDefinitionId());

	}
	@Test
	public void findTasks(){
		List<Task> tasks = processEngine.getTaskService().createTaskQuery().taskAssignee("ygz").list();
		for (Task task : tasks) {
			System.out.println("Assignee:"+task.getAssignee());
			System.out.println("id:"+task.getId());
			System.out.println("CreateTime:"+task.getCreateTime());
			System.out.println("InstanceId:"+task.getProcessInstanceId());
		}
	}
	@Test
	public void completeTask() {
		//processEngine.getTaskService().complete("2504");
		//processEngine.getTaskService().complete("7504");
		processEngine.getTaskService().complete("12502");
	}
}
