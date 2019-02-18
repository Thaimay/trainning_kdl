package jp.co.world.storedevelopment.model;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

import jp.co.world.storedevelopment.ModelTest;
import jp.co.world.storedevelopment.model.mapper.repository.AccountRepository;
import jp.co.world.storedevelopment.model.mapper.repository.RelatedTaskRepository;

public class RelatedTaskTest extends ModelTest {
    @Test
    public void create() {
        Account account = new AccountRepository().getHead().get();
        RelatedTask r = new RelatedTask(account);
        
        r.create();
        
        assertNotEquals(Long.valueOf(0), r.getId());        
        Optional<RelatedTask> result = new RelatedTaskRepository().findById(r.getId());
        
        assertTrue(result.isPresent());
    }
    
}
