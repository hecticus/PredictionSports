/*
 * Copyright 2012 Steve Chaloner
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package models;


import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.databind.node.ObjectNode;
import play.db.ebean.Model;
import be.objectify.deadbolt.core.models.Role;
import play.libs.Json;
import scala.Tuple2;
import scala.collection.JavaConversions;
import scala.collection.mutable.Buffer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Steve Chaloner (steve@objectify.be)
 */
@Entity
public class SecurityRole extends Model implements Role {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	public Long id;

	public String roleName;

	public static final Finder<Long, SecurityRole> find = new Finder<Long, SecurityRole>(
			Long.class, SecurityRole.class);

	@Override
	public String getName() {
		return roleName;
	}

	public static SecurityRole findByRoleName(String roleName) {
		return find.where().eq("roleName", roleName).findUnique();
	}

    public static scala.collection.immutable.List<Tuple2<String, String>> toSeq() {
        List<SecurityRole> securityRoles = SecurityRole.find.all();
        ArrayList<Tuple2<String, String>> proxy = new ArrayList<>();
        for(SecurityRole securityRole : securityRoles) {
            Tuple2<String, String> t = new Tuple2<>(securityRole.id.toString(), securityRole.getName());
            proxy.add(t);
        }
        Buffer<Tuple2<String, String>> roleBuffer = JavaConversions.asScalaBuffer(proxy);
        scala.collection.immutable.List<Tuple2<String, String>> roleList = roleBuffer.toList();
        return roleList;
    }

    public ObjectNode toJson() {
        ObjectNode response = Json.newObject();
        response.put("id", id);
        response.put("roleName", roleName);
        return response;
    }
}
