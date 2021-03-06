// Copyright 2017 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package codeu.model.store.basic;

import codeu.model.data.User;
import codeu.model.data.UserConversationMap;
import codeu.model.store.persistence.PersistentStorageAgent;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Store class that uses in-memory data structures to hold values and automatically loads from and
 * saves to PersistentStorageAgent. It's a singleton so all servlet classes can access the same
 * instance.
 */
public class ConversationMappingStore {

  /** Singleton instance of ConversationMappingStore. */
  private static ConversationMappingStore instance;

  /**
   * Returns the singleton instance of ConversationMappingStore that should be shared between all servlet classes.
   * Do not call this function from a test; use getTestInstance() instead.
   */
  public static ConversationMappingStore getInstance() {
    if (instance == null) {
      instance = new ConversationMappingStore(PersistentStorageAgent.getInstance());
    }
    return instance;
  }

  /**
   * Instance getter function used for testing. Supply a mock for PersistentStorageAgent.
   *
   * @param persistentStorageAgent a mock used for testing
   */
  public static ConversationMappingStore getTestInstance(PersistentStorageAgent persistentStorageAgent) {
    return new ConversationMappingStore(persistentStorageAgent);
  }

  /**
   * The PersistentStorageAgent responsible for loading UserConversationMaps from and saving UserConversationMaps to Datastore.
   */
  private PersistentStorageAgent persistentStorageAgent;

  /** The in-memory list of ConversationMapping. */
  private List<UserConversationMap> mappings;

  /** This class is a singleton, so its constructor is private. Call getInstance() instead. */
  private ConversationMappingStore(PersistentStorageAgent persistentStorageAgent) {
    this.persistentStorageAgent = persistentStorageAgent;
    mappings = new ArrayList<>();
  }

  public void addMapping(UserConversationMap mapping) {
  	mappings.add(mapping);
    persistentStorageAgent.writeThrough(mapping);
  }

  public List<UUID> getConversationsWithUserID(UUID userID) {

    List<UUID> conversationIDs = new ArrayList<>();

  	for (UserConversationMap mapping : mappings) {
  		if (mapping.getUserID().equals(userID)) {
  			conversationIDs.add(mapping.getConversationID());
  		}
  	}
  	return conversationIDs;
  }

  public void setMappings(List<UserConversationMap> mappings) {
    this.mappings = mappings;
  }

}
