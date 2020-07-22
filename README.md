# hibernate-orm-entity-graph-eager-fetch-of-lazy-field-bug

https://hibernate.atlassian.net/browse/HHH-14084 </br>
When fetching parent entity with child, which both have property with same name generates unexpected behaviour.

For example imagine you have Parent and Child entity. Both of entites have property named toys and marked as lazy. 
You fetch Parent entities with below graph which fetch child and toys of parent. 
Side effect of this operation is that toys of child will be eagearly fetched.
```
@NamedEntityGraph(
      name = "parentWithToysAndChild",
      attributeNodes = {
            @NamedAttributeNode("toys"),
            @NamedAttributeNode("child")
      }
)
```
