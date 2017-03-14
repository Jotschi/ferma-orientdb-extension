package com.gentics.ferma;

import com.gentics.ferma.model.Person;
import com.gentics.ferma.orientdb.OrientDBTrxFactory;
import com.syncleus.ferma.FramedGraph;
import com.tinkerpop.blueprints.impls.orient.OrientGraphFactory;

public class AbstractOrientDBTest {

	protected OrientGraphFactory graphFactory = new OrientGraphFactory("memory:tinkerpop").setupPool(4, 10);
	protected OrientDBTrxFactory graph = new OrientDBTrxFactory(graphFactory, "com.gentics.ferma.model");

	protected void manipulatePerson(FramedGraph graph, Person p) {
		p.setName("Changed " + System.currentTimeMillis());
		for (int i = 0; i < 10; i++) {
			Person friend = graph.addFramedVertex(Person.class);
			friend.setName("Friend " + i);
			p.addFriend(friend);
		}
		for (Person friend : p.getFriends()) {
			friend.setName("Changed Name " + System.currentTimeMillis());
		}
	}

	protected void addFriend(FramedGraph graph, Person p) {
		Person friend = graph.addFramedVertex(Person.class);
		friend.setName("NewFriend");
		p.addFriend(friend);
	}

	protected Person addPersonWithFriends(FramedGraph graph, String name) {
		Person p = graph.addFramedVertex(Person.class);
		p.setName(name);

		for (int i = 0; i < 10; i++) {
			Person friend = graph.addFramedVertex(Person.class);
			friend.setName("Friend " + i);
			p.addFriend(friend);
		}
		return p;
	}
}
