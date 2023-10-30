package com.swp.ZooManagement.apis.tickets;

import com.swp.ZooManagement.core.AbstractZooManagementService;
import com.swp.ZooManagement.errors.ValidationError;
import com.swp.ZooManagement.errors.ValidationErrorReport;
import com.swp.ZooManagement.errors.ZooManagementException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketsService extends AbstractZooManagementService<Ticket, Integer, CreateTicketDto, UpdateTicketDto, FilterTicketDto> {
    @Override
    protected void beforeCreate(Ticket entity) throws ZooManagementException {
        TicketsRepository repository = (TicketsRepository) this.repository;
        List<ValidationError> errors = new ArrayList<>();
        Optional<Ticket> findResult;

        // Check name
        findResult = repository.findByName(entity.getName());
        if (findResult.isPresent()) {
            errors.add(new ValidationError("name", entity.getName(), "This ticket name is already existed"));
        }

        if (!errors.isEmpty()) {
            throw new ZooManagementException(new ValidationErrorReport(errors));
        }
    }

    @Override
    protected void beforeUpdate(Ticket oldEntity, Ticket newEntity) throws ZooManagementException {
        TicketsRepository repository = (TicketsRepository) this.repository;
        List<ValidationError> errors = new ArrayList<>();
        Optional<Ticket> findResult;

        // Check name
        findResult = repository.findByName(newEntity.getName());
        if (findResult.isPresent() && !findResult.get().getId().equals(oldEntity.getId())) {
            errors.add(new ValidationError("name", newEntity.getName(), "This ticket name is already existed"));
        }

        if (!errors.isEmpty()) {
            throw new ZooManagementException(new ValidationErrorReport(errors));
        }

        oldEntity.setName(newEntity.getName());
        oldEntity.setDescription(newEntity.getDescription());
        oldEntity.setPrice(newEntity.getPrice());
    }

    @Override
    public Ticket delete(Integer id) throws ZooManagementException {
        return null;
    }
}
