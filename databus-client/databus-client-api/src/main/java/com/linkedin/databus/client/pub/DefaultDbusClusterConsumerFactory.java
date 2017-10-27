package com.linkedin.databus.client.pub;
/*
 *
 * Copyright 2013 LinkedIn Corp. All rights reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
*/


import java.util.Arrays;
import java.util.Collection;

public class DefaultDbusClusterConsumerFactory<T extends DatabusCombinedConsumer> implements DbusClusterConsumerFactory 
{
	  private final Class<T> _class;
	
	  public DefaultDbusClusterConsumerFactory(Class<T> c)
	  {
	    _class = c;
	  }

	@Override
	public Collection<DatabusCombinedConsumer> createPartitionedConsumers(
			DbusClusterInfo clusterInfo, DbusPartitionInfo partitionInfo) 
	{
		DatabusCombinedConsumer c;
		try {
			c = _class.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
		
		return Arrays.asList(c);
	}

}
