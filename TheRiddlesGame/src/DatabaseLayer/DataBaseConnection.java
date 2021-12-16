package DatabaseLayer;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.table.CloudTable;
import com.microsoft.azure.storage.table.CloudTableClient;
import com.microsoft.azure.storage.table.EntityProperty;
import com.microsoft.azure.storage.table.EntityResolver;
import com.microsoft.azure.storage.table.TableBatchOperation;
import com.microsoft.azure.storage.table.TableOperation;
import com.microsoft.azure.storage.table.TableQuery;
import com.microsoft.azure.storage.table.TableQuery.QueryComparisons;

import Model.Player;


/**Referened and modified from tutorial to apply the methods to the game
 * ThomasWeiss, Use the Azure Tables Client Library for java. Use the Azure Tables client library for Java | Microsoft Docs. Available at: https://docs.microsoft.com/en-us/azure/cosmos-db/table/how-to-use-java [Accessed November 14, 2021]. 
 * 
 * This class represents the the connection and methods used to send and retirved data using azure table storage
 * @author Sivapalan Siranjiv
 *Refer the repository-//https://github.com/Siranjiv/CISProject
 */


public class DataBaseConnection {

	/**
	 * Connection to the database api
	 */
	public static final String storageConnectionString = "DefaultEndpointsProtocol=https;AccountName=cisstoragesiranjiv;AccountKey=K9GRlJBEoIuspsPA0SsGf5jnEtUhHMtctS8So4gMmLtmXp6aDROowOeg8mpR5jGT9Zh1BMdJqhbyTUTg2n1Rhw==;EndpointSuffix=core.windows.net";

	Player player = null;

	
	public DataBaseConnection() {}
	
	
	/**
	 * method to send the player Registation records from login gui to the table api 
	 * @param firstName
	 * @param email
	 * @param password
	 */
	public void setDataRecords(String firstName, String email, String password) {

		try {
			// Retrieve storage account from connection-string.
			CloudStorageAccount storageAccount = CloudStorageAccount.parse(storageConnectionString);

			// Create the table client.
			CloudTableClient tableClient = storageAccount.createCloudTableClient();

			// Define a batch operation.
			TableBatchOperation batchOperation = new TableBatchOperation();

			// Create a cloud table object for the table.
			CloudTable cloudTable = tableClient.getTableReference("PlayerInfo");

			// Create a new player entity.
			Player player = new Player(firstName, email);
			player.setEmail(email);
			player.setPassword(password);

			// Create an operation to add the new player to the people table.
			TableOperation insertplayer1 = TableOperation.insertOrReplace(player);

			// Submit the operation to the table service.
			cloudTable.execute(insertplayer1);

		} catch (Exception e) {
			// Output the stack trace.
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to get the score of perticular player to view in ScoreBoardGUI
	 * @return
	 */
	public ArrayList getScoreData()
	{
		ArrayList Scorelist = new ArrayList();
		try
		{
		    // Define constants for filters.
		    final String PARTITION_KEY = "PartitionKey";
		    final String ROW_KEY = "RowKey";
		    final String TIMESTAMP = "Timestamp";
		    

		    // Retrieve storage account from connection-string.
		    CloudStorageAccount storageAccount =
		        CloudStorageAccount.parse(storageConnectionString);

		    // Create the table client.
		    CloudTableClient tableClient = storageAccount.createCloudTableClient();

		    // Create a cloud table object for the table.
		    CloudTable cloudTable = tableClient.getTableReference("PlayerInfo");



		    // Specify a partition query
		    TableQuery<Player> partitionQuery =
		        TableQuery.from(Player.class);

		    // Loop through the results, displaying information about the entity.
		    for (Player entity : cloudTable.execute(partitionQuery)) {
		    	
		    	Player p = new Player(entity.getPartitionKey(),null,entity.getScore(),null);
		    	Scorelist.add(p);
		      

		    }
		   
		    
		}
		catch (Exception e)
		{
		    // Output the stack trace.
		    e.printStackTrace();
		}
		return Scorelist;
		
	}
	
	/**
	 * Method to check/authenticate if player login credentials  are correct
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean Login(String username, String password )
	{
		boolean bool = false;
		try
		{
		    // Define constants for filters.
		    final String PARTITION_KEY = "PartitionKey";
		    final String ROW_KEY = "RowKey";
		    final String TIMESTAMP = "Timestamp";

		    // Retrieve storage account from connection-string.
		    CloudStorageAccount storageAccount =
		        CloudStorageAccount.parse(storageConnectionString);

		    // Create the table client.
		    CloudTableClient tableClient = storageAccount.createCloudTableClient();

		    // Create a cloud table object for the table.
		    CloudTable cloudTable = tableClient.getTableReference("PlayerInfo");



		    // Specify a partition query
		    TableQuery<Player> partitionQuery =
		        TableQuery.from(Player.class);


		    // Loop through the results, displaying information about the entity.
		    for (Player entity : cloudTable.execute(partitionQuery)) {
		    	
		    	if(username.equals(entity.getEmail())&& password.equals(entity.getPassword()))
		    	{
		    		bool= true;
		    	}
		        

		    }
		    
		}
		catch (Exception e)
		{
		    // Output the stack trace.
		    e.printStackTrace();
		}
		return bool;
		
	}
	
	/**
	 * Method used to retrive the UserName of player of the particular email address
	 * @param email
	 * @return
	 */
	public String retriveKeys(String email){
		String value=null;
		try
		{
		    // Define constants for filters.
			
		    final String PARTITION_KEY = "PartitionKey";
		    final String ROW_KEY = "RowKey";
		    final String TIMESTAMP = "Timestamp";
		    
		    
		    

		    // Retrieve storage account from connection-string.
		    CloudStorageAccount storageAccount =
		        CloudStorageAccount.parse(storageConnectionString);

		    // Create the table client.
		    CloudTableClient tableClient = storageAccount.createCloudTableClient();

		    // Create a cloud table object for the table.
		    CloudTable cloudTable = tableClient.getTableReference("PlayerInfo");

		    // Create a filter condition where the partition key is "email".
		    String rowFilter = TableQuery.generateFilterCondition(
		    		ROW_KEY,
		        QueryComparisons.EQUAL,
		        email);

		    // Specify a partition query, using "email" as the partition key filter.
		    TableQuery<Player> partitionQuery =
		        TableQuery.from(Player.class)
		        .where(rowFilter);

		    // Loop through the results, displaying information about the entity.
		    for (Player entity : cloudTable.execute(partitionQuery)) {

		    	value= entity.getPartitionKey();

		    }
		    
		}
		catch (Exception e)
		{
		    // Output the stack trace.
		    e.printStackTrace();
		}
		return value;
		

	}
	
	/**
	 * Store the Code of the Player who is currently logged in
	 * @param name
	 * @param email
	 * @param Score
	 */
	public void ModifyScore(String name,String email,String Score)
	{
		try
		{
		    // Retrieve storage account from connection-string.
		    CloudStorageAccount storageAccount =
		        CloudStorageAccount.parse(storageConnectionString);

		    // Create the table client.
		    CloudTableClient tableClient = storageAccount.createCloudTableClient();

		    // Create a cloud table object for the table.
		    CloudTable cloudTable = tableClient.getTableReference("PlayerInfo");

		    // Retrieve the entity with partition key and row key.
		    TableOperation retrievePlayer =
		        TableOperation.retrieve(name, email, Player.class);

		    // Submit the operation to the table service and get the specific entity.
		    Player specificEntity =
		        cloudTable.execute(retrievePlayer).getResultAsType();

		    // Specify a new score
		    specificEntity.setScore(Score);

		    // Create an operation to replace the entity.
		    TableOperation replaceEntity = TableOperation.replace(specificEntity);

		    // Submit the operation to the table service.
		    cloudTable.execute(replaceEntity);
		}
		catch (Exception e)
		{
		    // Output the stack trace.
		    e.printStackTrace();
		}
		
	}
	
	/**
	 * Method used to check if the email is used to register again for the game
	 * @param email
	 * @return
	 */
	public boolean verfyEmail(String email)
	{
		boolean bool = false;
		try
		{
		    // Define constants for filters.
		    final String PARTITION_KEY = "PartitionKey";
		    final String ROW_KEY = "RowKey";
		    final String TIMESTAMP = "Timestamp";

		    // Retrieve storage account from connection-string.
		    CloudStorageAccount storageAccount =
		        CloudStorageAccount.parse(storageConnectionString);

		    // Create the table client.
		    CloudTableClient tableClient = storageAccount.createCloudTableClient();

		    // Create a cloud table object for the table.
		    CloudTable cloudTable = tableClient.getTableReference("PlayerInfo");


		    // Specify a partition query
		    TableQuery<Player> partitionQuery =
		        TableQuery.from(Player.class);


		    // Loop through the results, displaying information about the entity.
		    for (Player entity : cloudTable.execute(partitionQuery)) {

		       
		        if(entity.getEmail().equals(email))
		        {
		        	bool=true;
		        }
		       
		    }
		}
		catch (Exception e)
		{
		    // Output the stack trace.
		    e.printStackTrace();
		}
		
		return bool;
	}
	
	/**
	 * Method used to check if the Player Name is used to register again for the game 
	 * @param name
	 * @return
	 */
	public boolean verifyUname(String name)
	{
		boolean bool = false;
		try
		{
		    // Define constants for filters.
		    final String PARTITION_KEY = "PartitionKey";
		    final String ROW_KEY = "RowKey";
		    final String TIMESTAMP = "Timestamp";

		    // Retrieve storage account from connection-string.
		    CloudStorageAccount storageAccount =
		        CloudStorageAccount.parse(storageConnectionString);

		    // Create the table client.
		    CloudTableClient tableClient = storageAccount.createCloudTableClient();

		    // Create a cloud table object for the table.
		    CloudTable cloudTable = tableClient.getTableReference("PlayerInfo");

		  

		    // Specify a partition query
		    TableQuery<Player> partitionQuery =
		        TableQuery.from(Player.class);

		    // Loop through the results, displaying information about the entity.
		    for (Player entity : cloudTable.execute(partitionQuery)) {
		       
		       
		        if(entity.getPartitionKey().equals(name))
		        {
		        	bool=true;
		        }
		       
		    }
		}
		catch (Exception e)
		{
		    // Output the stack trace.
		    e.printStackTrace();
		}
		
		return bool;	
	}

}
