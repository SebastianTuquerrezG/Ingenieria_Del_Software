'use strict'

const amqp = require('amqplib')
const queue = process.env.QUEUE || 'hello'

function intensiveOperation(){
    let i = 1e9
    while(i--){

    }
}

async function subscriber(){
    const conn = await amqp.connect('amqp://localhost')
    const channel = await conn.createChannel()
    await channel.assertQueue(queue)

    intensiveOperation()

    await channel.consume(queue, message => {
        const content = JSON.parse(message.content.toString())

        console.log(`Message received: from "${queue}" queue`)
        console.log(content)

        channel.ack(message)
    })
}

subscriber().catch(error => {
    console.error(error)
    process.exit(1)
})