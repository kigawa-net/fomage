# Repository Fix

The `FonsoleRepository` interface was modified to use custom MongoDB queries with the `@Query` annotation. This allows us to specify exactly how to find documents in a specific collection:

1. For `findByCollection`, we added `@Query("{ '_collection': ?0 }")` which will look for documents where the `_collection` field matches the provided collection name.
2. For `findByIdAndCollection`, we added `@Query("{ '_id': ?0, '_collection': ?1 }")` which will look for a document with the specified ID and collection name.

This approach assumes that documents in MongoDB have a field named `_collection` that stores the collection name. This is a common pattern when working with MongoDB in a more dynamic way, where the actual collection name might be different from the logical collection name stored in the document.

The original error was:
```
Could not create query for public abstract java.util.List net.kigawa.fomage.core.repository.FonsoleRepository.findByCollection(java.lang.String); Reason: No property 'collection' found for type 'Map'
```

This occurred because Spring Data MongoDB was trying to create a query based on the method name `findByCollection`, looking for a property called 'collection' in the Map type, but there was no such property in the generic Map.